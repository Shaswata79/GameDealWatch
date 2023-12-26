package shaswata.gameservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shaswata.gameservice.dto.ItemDto;
import shaswata.gameservice.dto.PriceDateDto;
import shaswata.gameservice.dto.PriceDto;
import shaswata.gameservice.service.UserGameService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game/user")
public class UserController {

    private final UserGameService userGameService;

    /**
     * View items/games of a specific store (Steam, Blizzard, EA)
     * @param storeName
     * @return
     */
    @GetMapping("/viewItems")
    public ResponseEntity<?> viewStoreItems(@RequestParam String storeName) {
        try {
            List<ItemDto> itemDtoList = userGameService.viewStoreItems(storeName);
            return new ResponseEntity<>(itemDtoList, HttpStatus.OK);
        } catch (Exception e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * View a game by ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> viewItem(@PathVariable Long id) {
        try {
            ItemDto itemDto = userGameService.viewItem(id);
            return new ResponseEntity<>(itemDto, HttpStatus.OK);
        } catch (Exception e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get all the games by a list of IDs
     * Endpoint accessed by game-list-service only (in createList service method)
     * @param ids
     * @return
     */
    @GetMapping("/getItems")
    public ResponseEntity<?> getStoreItems(@RequestParam List<String> ids) {
        try {
            List<ItemDto> itemDtoList = userGameService.getItemsByID(ids);
            return new ResponseEntity<>(itemDtoList, HttpStatus.OK);
        } catch (Exception e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get a game by ID
     * Endpoint accessed by game-list-service (in addItemToList service method)
     * @param id
     * @return
     */
    @GetMapping("/getItem")
    public ResponseEntity<?> getStoreItem(@RequestParam String id) {
        try {
            ItemDto itemDtoList = userGameService.getItemByID(id);
            return new ResponseEntity<>(itemDtoList, HttpStatus.OK);
        } catch (Exception e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get the current price of all games
     * Endpoint accessed by game-list-service only (in updatePrices service method)
     * @return
     */
    @GetMapping("/latestPrices")
    public ResponseEntity<?> getLatestPrices() {
        try {
            List<PriceDto> priceDtos = userGameService.getLatestPrices();
            return new ResponseEntity<>(priceDtos, HttpStatus.OK);
        } catch (Exception e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * View the price history of a specific game
     * @param id
     * @return
     */
    @GetMapping("/{id}/history")
    public ResponseEntity<?> viewPriceHistory(@PathVariable Long id) {
        try {
            List<PriceDateDto> priceDateDto = userGameService.viewPriceHistory(id);
            return new ResponseEntity<>(priceDateDto, HttpStatus.OK);
        } catch (Exception e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
