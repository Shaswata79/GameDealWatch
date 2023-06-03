package shaswata.gamelistservice.controller;

import shaswata.gamelistservice.dto.GameListDto;
import shaswata.gamelistservice.service.AdminListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/list-service/admin")
public class AdminController {

    private final AdminListService adminListService;

    @GetMapping("/userList/{email}")
    public ResponseEntity<?> viewUser(@PathVariable String email){
        try{
            GameListDto listDto = adminListService.getGameListByUser(email);
            return new ResponseEntity<>(listDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
