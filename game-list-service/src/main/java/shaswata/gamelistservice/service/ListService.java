package shaswata.gamelistservice.service;


import shaswata.gamelistservice.dto.GameListDto;
import shaswata.gamelistservice.dto.ListItemDto;
import shaswata.gamelistservice.model.GameList;
import shaswata.gamelistservice.model.ListItem;

import java.util.ArrayList;
import java.util.List;


public abstract class ListService {


    protected static GameListDto gameListToDTO(GameList list){
        GameListDto dto = new GameListDto();
        dto.setId(list.getId());
        dto.setUserEmail(list.getUserEmail());
        dto.setListName(list.getListName());
        dto.setListName(list.getListName());
        List<ListItemDto> copiedItemList = new ArrayList<>();
        for (ListItem item : list.getItems()) { //deep copy
            ListItemDto itemDto = listItemToDTO(item);
            copiedItemList.add(itemDto);
        }
        dto.setItems(copiedItemList);
        return dto;
    }

    protected static ListItemDto listItemToDTO(ListItem item){
        ListItemDto dto = new ListItemDto();
        dto.setItemID(item.getItemID());
        dto.setGameName(item.getGameName());
        dto.setThreshold(item.getThreshold());
        return dto;
    }

}
