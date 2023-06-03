package shaswata.gamelistservice.service;

import shaswata.gamelistservice.dto.GameListDto;
import shaswata.gamelistservice.dto.UserDto;
import shaswata.gamelistservice.model.GameList;
import shaswata.gamelistservice.repository.GameListRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class AdminListService extends ListService{

    private final GameListRepository gameListRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public GameListDto getGameListByUser(String email) throws Exception {
        if(email == null || email == ""){
            throw new Exception("User email cannot be empty!");
        }

        GameList gameList = gameListRepo.findGameListByUserEmail(email);
        if(gameList == null){
            throw new Exception("User does not have any game list!");
        }

        return gameListToDTO(gameList);
    }


    protected UserDto getUser(String email){
        UserDto userDto = restTemplate.getForObject("http://localhost:8082/user-service/user/get/" + email, UserDto.class);
        return userDto;
    }



}
