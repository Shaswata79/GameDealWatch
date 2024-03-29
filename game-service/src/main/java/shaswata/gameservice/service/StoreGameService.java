package shaswata.gameservice.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import shaswata.gameservice.dto.ItemDto;
import shaswata.gameservice.model.Item;
import shaswata.gameservice.model.PriceData;
import shaswata.gameservice.model.Store;
import shaswata.gameservice.repository.ItemRepository;
import shaswata.gameservice.repository.PriceDataRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreGameService {

    private final ItemRepository itemRepo;
    private final PriceDataRepository priceDataRepo;
    private final SteamScraper steamScraper;


    /**
     * Calls the web scraper for each store and updates database
     * @param storeName
     * @return
     * @throws Exception
     */
    public List<ItemDto> updateGameInfo(String storeName) throws Exception {
        switch (Store.valueOf(storeName)) {
            case Steam:
                List<ItemDto> itemDtoList = steamScraper.scrape();
                updateItemDB(itemDtoList, Store.Steam);
                return itemDtoList;
            case Blizzard:
                System.out.println("Blizzard");
                return null;
            case EA:
                System.out.println("EA");
                return null;
            default:
                throw new Exception("No such store found");
        }

    }

    /**
     * Updates the game database after new data is scraped from a store's website
     * @param itemDtoList
     * @param store
     * @throws Exception
     */
    @Transactional
    private void updateItemDB(List<ItemDto> itemDtoList, Store store) throws Exception {
        try{
            for(ItemDto itemDto : itemDtoList){
                Item item = itemRepo.findItemByStoreAndGame(store, itemDto.getGame());
                if(item != null){
                    List<PriceData> prices = item.getPrices();
                    if(prices.size() >= 10){
                        prices.remove(9);
                    }
                    prices.add(0, new PriceData(itemDto.getCurrentPrice(), LocalDate.now()));
                    item.setPrices(prices);

                }else{
                    item = new Item();
                    item.setGame(itemDto.getGame());
                    item.setStore(store);
                    item.setUrl(itemDto.getUrl());
                    List<PriceData> prices = new ArrayList<>();
                    prices.add(new PriceData(itemDto.getCurrentPrice(), LocalDate.now()));
                    item.setPrices(prices);
                }
                itemRepo.save(item);
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }


    }


    /**
     * Updates the game database for each store
     * Executes daily at midnight
     * @throws Exception
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void updateGameInfoDaily() throws Exception {
        updateGameInfo("Steam");
        updateGameInfo("Blizzard");
        updateGameInfo("EA");
    }


}
