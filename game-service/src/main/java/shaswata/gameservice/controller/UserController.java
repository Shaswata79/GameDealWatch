package shaswata.gameservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shaswata.gameservice.dto.ItemDto;
import shaswata.gameservice.dto.PriceDataDto;
import shaswata.gameservice.service.UserGameService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game/user")
public class UserController {

    private final UserGameService userGameService;

    @GetMapping("/viewItems")
    public ResponseEntity<?> viewStoreItems(@RequestParam String storeName) {
        try {
            List<ItemDto> itemDtoList = userGameService.viewStoreItems(storeName);
            return new ResponseEntity<>(itemDtoList, HttpStatus.OK);
        } catch (Exception e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> viewItem(@PathVariable Long id) {
        try {
            ItemDto itemDto = userGameService.viewItem(id);
            return new ResponseEntity<>(itemDto, HttpStatus.OK);
        } catch (Exception e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getItems")
    public ResponseEntity<?> getStoreItems(@RequestParam List<String> ids) {
        try {
            List<ItemDto> itemDtoList = userGameService.getItemsByID(ids);
            return new ResponseEntity<>(itemDtoList, HttpStatus.OK);
        } catch (Exception e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getItem")
    public ResponseEntity<?> getStoreItem(@RequestParam String id) {
        try {
            ItemDto itemDtoList = userGameService.getItemByID(id);
            return new ResponseEntity<>(itemDtoList, HttpStatus.OK);
        } catch (Exception e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}/history")
    public ResponseEntity<?> viewPriceHistory(@PathVariable Long id) {
        try {
            List<PriceDataDto> priceDataDto = userGameService.viewPriceHistory(id);
            return new ResponseEntity<>(priceDataDto, HttpStatus.OK);
        } catch (Exception e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
