package shaswata.gameservice.controller;

import shaswata.gameservice.dto.ItemDto;
import shaswata.gameservice.service.StoreGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game-service/store")
public class StoreController {

    private final StoreGameService storeGameService;

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
