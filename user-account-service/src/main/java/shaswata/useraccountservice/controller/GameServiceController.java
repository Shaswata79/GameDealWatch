//package shaswata.useraccountservice.controller;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import shaswata.useraccountservice.dto.UserDto;
//import shaswata.useraccountservice.service.UserService;
//
//@RestController
//@RequestMapping("/accounts/gamelist")
//@RequiredArgsConstructor
//public class GameServiceController {
//
//    private final UserService userService;
//
//    @PutMapping("/updateUser")
//    public ResponseEntity<?> updateUserList(@RequestParam String email, @RequestParam Long gameListID){
//        try{
//            UserDto userDto = userService.updateList(email, gameListID);
//            return new ResponseEntity<>(userDto, HttpStatus.OK);
//
//        }catch(Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//}
