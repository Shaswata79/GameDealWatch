package shaswata.gameservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shaswata.gameservice.dto.ItemDto;
import shaswata.gameservice.service.StoreGameService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game/store")
public class StoreController {

    private final StoreGameService storeGameService;

    /**
     * Update the database of games from a specific store (Steam, Blizzard, EA)
     * @param storeName
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateStoreItems(@RequestParam String storeName) {
        try {
            List<ItemDto> itemDtoList = storeGameService.updateGameInfo(storeName);
            return new ResponseEntity<>(itemDtoList, HttpStatus.OK);
        } catch (Exception e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
