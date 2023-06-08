package shaswata.gamelistservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import shaswata.gamelistservice.model.GameList;

import java.util.List;

public interface GameListRepository extends JpaRepository<GameList, Long> {

    GameList findGameListById(Long Id);
    GameList findGameListByUserEmail(String email);
    List<GameList> findAll();
    void deleteGameListById(Long Id);
    void deleteGameListByUserEmail(String email);
    void deleteAll();

}
