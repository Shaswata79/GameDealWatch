package shaswata.gamelistservice.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shaswata.gamelistservice.dto.GameListDto;
import shaswata.gamelistservice.dto.ItemDto;
import shaswata.gamelistservice.model.GameList;
import shaswata.gamelistservice.model.ListItem;
import shaswata.gamelistservice.repository.GameListRepository;
import shaswata.gamelistservice.repository.ListItemRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserListService extends ListService{

    private final ListItemRepository listItemRepo;
    private final GameListRepository gameListRepo;
    private final GameServiceClient gameServiceClient;
    private final UserAccountServiceClient userAccountServiceClient;



    @Transactional
    public GameListDto createList(String email, List<String> itemIDs) throws Exception {
        if(email == null || email == ""){
            throw new Exception("User email cannot be empty!");
        }
        if(itemIDs.isEmpty()){
            throw new Exception("Item list cannot be empty!");
        }

        GameList gameList = new GameList();
        List<ItemDto> items = gameServiceClient.getItems(itemIDs);
        System.out.println(Arrays.toString(items.toArray()));
        List<ListItem> itemList = setThresholds(items);
        gameList.setItems(itemList);
        gameList.setUserEmail(email);

        gameListRepo.save(gameList);
        userAccountServiceClient.updateUserListID(email, gameList.getId().toString());
        return gameListToDTO(gameList);
    }

    @Transactional
    public GameListDto addItemToList(String email, Long itemID) throws Exception{
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
        listItem.setThreshold(itemDto.getCurrentPrice() * 0.75);
        listItemRepo.save(listItem);


        gameList.getItems().add(listItem);
        gameListRepo.save(gameList);
        return gameListToDTO(gameList);
    }


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

    @Transactional
    public String deleteList(String email) throws Exception{
        if(email == null || email == ""){
            throw new Exception("User email cannot be empty!");
        }
        gameListRepo.deleteGameListByUserEmail(email);
        return "List deleted successfully";
    }


    @Transactional
    private List<ListItem> setThresholds(List<ItemDto> items){
        List<ListItem> listItems = new ArrayList<>();
        for(ItemDto item : items){
            Double price = item.getCurrentPrice();
            double threshold = price * 0.75;
            ListItem listItem = new ListItem();
            listItem.setThreshold(threshold);
            listItem.setItemID(item.getId());
            listItemRepo.save(listItem);
            listItems.add(listItem);
        }
        return listItems;
    }


}
