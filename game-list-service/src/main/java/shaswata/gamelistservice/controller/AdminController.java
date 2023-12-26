package shaswata.gamelistservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shaswata.gamelistservice.dto.GameListDto;
import shaswata.gamelistservice.dto.UserDto;
import shaswata.gamelistservice.service.AdminListService;
import shaswata.gamelistservice.service.UserAccountServiceClient;

@RestController
@RequiredArgsConstructor
@RequestMapping("/list/admin")
public class AdminController {

    private final AdminListService adminListService;
    private final UserAccountServiceClient userAccountServiceClient;

    /**
     * View a user's game list by email
     * @param email
     * @return
     */
    @GetMapping("/userList/{email}")
    public ResponseEntity<?> viewUserList(@PathVariable String email){
        try{
            GameListDto listDto = adminListService.getGameListByUser(email);
            return new ResponseEntity<>(listDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
