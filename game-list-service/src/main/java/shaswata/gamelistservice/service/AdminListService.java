package shaswata.gamelistservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shaswata.gamelistservice.dto.GameListDto;
import shaswata.gamelistservice.model.GameList;
import shaswata.gamelistservice.repository.GameListRepository;


@Service
@RequiredArgsConstructor
public class AdminListService extends ListService{

    private final GameListRepository gameListRepo;

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




}
