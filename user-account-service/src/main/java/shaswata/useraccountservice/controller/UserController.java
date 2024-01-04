package shaswata.useraccountservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shaswata.useraccountservice.dto.UserDto;
import shaswata.useraccountservice.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts/user")
public class UserController {

    private final UserService userService;

    /**
     * Create a user account after creating an account on authorisation server
     * @param userDto
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
        try{
            userDto = userService.createUser(userDto);
            return new ResponseEntity<>(userDto, HttpStatus.OK);

        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Update user's game list ID after a list is created in game-list-service
     * Endpoint accessed by game-list-service only (in createList service method)
     * @param email
     * @param listID
     * @return
     */
    @PostMapping("/updateListID")
    public ResponseEntity<?> updateListID(@RequestParam String email, @RequestParam String listID){
        try{
            UserDto userDto = userService.updateList(email, Long.valueOf(listID));
            return new ResponseEntity<>(userDto, HttpStatus.OK);

        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * View current logged-in user's account
     * @param email
     * @return
     */
    @GetMapping("/get/{email}")
    public ResponseEntity<?> viewUser(@PathVariable String email){
        try{
            UserDto user = userService.getUser(email);
            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
