package gamedealwatch.gameservice.service;

import gamedealwatch.gameservice.dto.ItemDto;
import gamedealwatch.gameservice.dto.PriceDataDto;
import gamedealwatch.gameservice.model.Item;
import gamedealwatch.gameservice.model.PriceData;
import gamedealwatch.gameservice.model.Store;
import gamedealwatch.gameservice.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserGameService {

    private final ItemRepository itemRepo;

    @Transactional
    public List<ItemDto> viewStoreItems(String storeName){
        List<Item> items = itemRepo.findItemsByStore(Store.valueOf(storeName));
        List<ItemDto> itemDtoList = items.stream()
                                        .map(UserGameService::itemToDTO)
                                        .collect(Collectors.toList());
        return itemDtoList;
    }

    @Transactional
    public ItemDto viewItem(Long id) throws Exception {
        Item item = itemRepo.findItemById(id);
        if(item == null){
            throw new Exception("Item does not exist!");
        }
        return itemToDTO(item);
    }

    @Transactional
    public List<ItemDto> getItemsByID(List<String> ids) throws Exception {
        List<ItemDto> itemDtoList = new ArrayList<>();
        for(String strId : ids){
            Long id = Long.valueOf(strId);
            Item item = itemRepo.findItemById(id);
            if(item == null){
                throw new Exception("Item with id " + id + " does not exist!");
            }
            itemDtoList.add(itemToDTO(item));
        }
        return itemDtoList;
    }

    @Transactional
    public ItemDto getItemByID(String strId) throws Exception {
        Long id = Long.valueOf(strId);
        Item item = itemRepo.findItemById(id);
        if(item == null){
            throw new Exception("Item does not exist!");
        }
        ItemDto itemDto = itemToDTO(item);
        return itemDto;
    }


    @Transactional
    public List<PriceDataDto> viewPriceHistory(Long itemId) throws Exception {
        Item item = itemRepo.findItemById(itemId);
        if(item == null){
            throw new Exception("Item does not exist!");
        }
        List<PriceData> priceDataList = item.getPrices();
        List<PriceDataDto> priceDataDtos = new ArrayList<>();
        for (PriceData priceData : priceDataList){
            priceDataDtos.add(priceDataToDTO(priceData));
        }
        return priceDataDtos;
    }


    static ItemDto itemToDTO(Item item){
        ItemDto dto = new ItemDto();
        dto.setGame(item.getGame());
        dto.setStoreName(item.getStore().toString());
        dto.setId(item.getId());
        dto.setUrl(item.getUrl());
        dto.setCurrentPrice(item.getPrices().get(0).getPrice());
        return dto;
    }


    static PriceDataDto priceDataToDTO(PriceData priceData){
        PriceDataDto dto = new PriceDataDto();
        dto.setDate(priceData.getDate());
        dto.setPrice(priceData.getPrice());
        return dto;
    }


}
