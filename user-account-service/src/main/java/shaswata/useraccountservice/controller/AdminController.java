package shaswata.useraccountservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shaswata.useraccountservice.dto.AdminDto;
import shaswata.useraccountservice.dto.UserDto;
import shaswata.useraccountservice.service.AdminService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts/admin")
public class AdminController {

    private final AdminService adminService;

    /**
     * Create an admin account after creating an account on authorisation server
     * @param adminDto
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody AdminDto adminDto){
        try{
            adminDto = adminService.createAdmin(adminDto);
            return new ResponseEntity<>(adminDto, HttpStatus.OK);

        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    /**
     * View all registered user accounts as admin
     * @return
     */
    @GetMapping("/viewAllUsers")
    public ResponseEntity<?> viewAllUsers(){
        try{
            List<UserDto> allUsers = adminService.getAllUsers();
            return new ResponseEntity<>(allUsers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/viewUser/{email}")
    public ResponseEntity<?> viewUser(@PathVariable String email){
        try{
            UserDto user = adminService.getUser(email);
            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
