package shaswata.gamelistservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shaswata.gamelistservice.dto.GameListDto;
import shaswata.gamelistservice.service.UserListService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/list/user")
public class UserController {

    private final UserListService userListService;

    @PostMapping("/createList")
    public ResponseEntity<?> createList(@RequestParam String email, @RequestParam List<String> ids){
        try{
            GameListDto listDto = userListService.createList(email, ids);
            return new ResponseEntity<>(listDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/add")
    public ResponseEntity<?> addToList(@RequestParam String email, @RequestParam Long id){
        try{
            GameListDto listDto = userListService.addItemToList(email, id);
            return new ResponseEntity<>(listDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/remove")
    public ResponseEntity<?> removeFromList(@RequestParam String email, @RequestParam Long id){
        try{
            GameListDto listDto = userListService.removeItemFromList(email, id);
            return new ResponseEntity<>(listDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/userList/{email}")
    public ResponseEntity<?> viewUser(@PathVariable String email){
        try{
            GameListDto listDto = userListService.viewOwnList(email);
            return new ResponseEntity<>(listDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteList(@PathVariable String email){
        try{
            String message = userListService.deleteList(email);
            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


//    @GetMapping("/getItems")
//    public ResponseEntity<?> viewStoreItems(@RequestParam List<String> ids) {
//        try {
//            List<ItemDto> itemDtoList = userListService.getItems(ids);
//            return new ResponseEntity<>(itemDtoList, HttpStatus.OK);
//        } catch (Exception e ){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }


}
