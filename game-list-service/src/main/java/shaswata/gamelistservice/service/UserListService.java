package shaswata.gamelistservice.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import shaswata.amqp.RabbitMQMessageProducer;
import shaswata.gamelistservice.dto.GameListDto;
import shaswata.gamelistservice.dto.ItemDto;
import shaswata.gamelistservice.dto.NotificationRequest;
import shaswata.gamelistservice.dto.PriceDto;
import shaswata.gamelistservice.model.GameList;
import shaswata.gamelistservice.model.ListItem;
import shaswata.gamelistservice.repository.GameListRepository;
import shaswata.gamelistservice.repository.ListItemRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserListService extends ListService{

    private final ListItemRepository listItemRepo;
    private final GameListRepository gameListRepo;
    private final GameServiceClient gameServiceClient;
    private final UserAccountServiceClient userAccountServiceClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;


    /**
     * Create an empty game list for user
     * Update user's list ID by calling user-account-service
     * @param email
     * @return
     * @throws Exception
     */
    @Transactional
    public GameListDto createList(String email, String listName) throws Exception {
        if(email == null || email == ""){
            throw new Exception("User email cannot be empty!");
        }

        if(gameListRepo.findGameListByUserEmail(email) != null){
            return gameListToDTO(gameListRepo.findGameListByUserEmail(email));
        }

        GameList gameList = new GameList();
        List<ListItem> itemList = new ArrayList<>();
        gameList.setItems(itemList);
        gameList.setUserEmail(email);
        gameList.setListName(listName);

        gameListRepo.save(gameList);
        userAccountServiceClient.updateUserListID(email, gameList.getId().toString());
        return gameListToDTO(gameList);
    }

    /**
     * Add a game to the existing list
     * Fetch game delails by calling game-service
     * @param email
     * @param itemID
     * @param threshold
     * @return
     * @throws Exception
     */
    @Transactional
    public GameListDto addItemToList(String email, Long itemID, Double threshold) throws Exception{
        if(email == null || email == ""){
            throw new Exception("User email cannot be empty!");
        }
        if(itemID == null){
            throw new Exception("Item cannot be empty!");
        }
        GameList gameList = gameListRepo.findGameListByUserEmail(email);
        if(gameList == null){
            throw new Exception("The user does not have a list!");
        }

        ItemDto itemDto = gameServiceClient.getItem(itemID.toString());
        for(ListItem item : gameList.getItems()){
            if(item.getItemID() == itemID){
                throw new Exception("User's game list already contains the item!");
            }
        }
        ListItem listItem = new ListItem();
        listItem.setItemID(itemDto.getId());
        listItem.setGameName(itemDto.getGame());
        listItem.setThreshold(threshold);
        listItemRepo.save(listItem);

        gameList.getItems().add(listItem);
        gameListRepo.save(gameList);
        return gameListToDTO(gameList);
    }

    /**
     * Remove a game from list
     * @param email
     * @param itemID
     * @return
     * @throws Exception
     */
    @Transactional
    public GameListDto removeItemFromList(String email, Long itemID) throws Exception{
        if(email == null || email == ""){
            throw new Exception("User email cannot be empty!");
        }
        if(itemID == null){
            throw new Exception("Item cannot be empty!");
        }

        GameList gameList = gameListRepo.findGameListByUserEmail(email);
        List<ListItem> listItems = gameList.getItems();
        for(ListItem listItem : listItems){
            if(listItem.getItemID() == itemID){
                listItems.remove(listItem);
                listItemRepo.deleteListItemById(listItem.getId());
                break;
            }
        }

        gameListRepo.save(gameList);
        return gameListToDTO(gameList);
    }

    /**
     * View game list of current logged-in user
     * @param email
     * @return
     * @throws Exception
     */
    @Transactional
    public GameListDto viewOwnList(String email) throws Exception {
        if(email == null || email == ""){
            throw new Exception("User email cannot be empty!");
        }

        GameList gameList = gameListRepo.findGameListByUserEmail(email);
        if(gameList == null){
            throw new Exception("User does not have a list!");
        }
        return gameListToDTO(gameList);
    }

    /**
     * Delete a user's game list
     * @param email
     * @return
     * @throws Exception
     */
    @Transactional
    public String deleteList(String email) throws Exception{
        if(email == null || email == ""){
            throw new Exception("User email cannot be empty!");
        }
        gameListRepo.deleteGameListByUserEmail(email);
        return "List deleted successfully";
    }

    /**
     * Call the sendPriceAlert() function daily at 1 am (since game prices are updated at midnight by game-service)
     * @throws Exception
     */
    @Scheduled(cron = "0 0 1 * * *")
    public void sendPriceAlertDaily() throws Exception {
        sendPriceAlert();
    }

    /**
     * Check current prices of all games
     * For all the game lists in the system, send email alerts to users for game prices which are below threshold
     * @throws Exception
     */
    @Transactional
    public void sendPriceAlert() throws Exception {
        // first get the latest prices
        List<PriceDto> priceDtoList = gameServiceClient.getLatestPrices();
        HashMap<Long, Double> latestPrices = new HashMap<>();
        HashMap<Long, String> games = new HashMap<>();
        for(PriceDto priceDto : priceDtoList){
            latestPrices.put(priceDto.getId(), priceDto.getCurrentPrice());
            games.put(priceDto.getId(), priceDto.getGame());
        }

        // then check for prices below threshold
        String game;
        String userEmail;
        List<GameList> gameLists = gameListRepo.findAll();
        for(GameList gameList : gameLists){                 // for every game list
            for(ListItem item : gameList.getItems()){       // for every game in game list
                Double latestPrice = latestPrices.get(item.getItemID());
                if(latestPrice < item.getThreshold()){   // compare the latest price with threshold
                    game = games.get(item.getItemID());
                    userEmail = gameList.getUserEmail();
                    System.out.println("PRICE ALERT: " + game + " is only $" + latestPrice + ", buy it now!");
                    sendPriceAlertEmail(game, userEmail, latestPrice);
                }
            }
        }
    }

    private void sendPriceAlertEmail(String game, String email, Double currentPrice){
        String message = game + " is only $" + currentPrice + ", buy it now!";

        // RabbitMQ
        NotificationRequest notificationRequest = new NotificationRequest(email, message);
        rabbitMQMessageProducer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");
    }

}
