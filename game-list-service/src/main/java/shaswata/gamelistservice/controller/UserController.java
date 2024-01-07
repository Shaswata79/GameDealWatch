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

    /**
     * Create an empty game list for user
     * @param listDto
     * @return
     */
    @PostMapping("/createList")
    public ResponseEntity<?> createList(@RequestBody GameListDto listDto){
        try{
            System.out.println(listDto.getUserEmail());
            listDto = userListService.createList(listDto.getUserEmail(), listDto.getListName());
            return new ResponseEntity<>(listDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Add a game to the existing list
     * @param email
     * @param id
     * @param threshold
     * @return
     */
    @PutMapping("/add")
    public ResponseEntity<?> addToList(@RequestParam String email, @RequestParam Long id, @RequestParam Double threshold){
        try{
            GameListDto listDto = userListService.addItemToList(email, id, threshold);
            return new ResponseEntity<>(listDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Remove a game from list
     * @param email
     * @param id
     * @return
     */
    @PutMapping("/remove")
    public ResponseEntity<?> removeFromList(@RequestParam String email, @RequestParam Long id){
        try{
            GameListDto listDto = userListService.removeItemFromList(email, id);
            return new ResponseEntity<>(listDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * View game list of current logged-in user
     * @param email
     * @return
     */
    @GetMapping("/userList/{email}")
    public ResponseEntity<?> viewUser(@PathVariable String email){
        try{
            GameListDto listDto = userListService.viewOwnList(email);
            return new ResponseEntity<>(listDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete a user's game list
     * @param email
     * @return
     */
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteList(@PathVariable String email){
        try{
            String message = userListService.deleteList(email);
            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint to update prices manually (Only used for testing)
     * @return
     */
    @GetMapping("/updatePrices")
    public ResponseEntity<?> updatePrices(){
        try{
            userListService.sendPriceAlert();
            return new ResponseEntity<>("Price alert emails sent!", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
