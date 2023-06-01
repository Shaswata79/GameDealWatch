package gamedealwatch.gamelistservice.repository;


import gamedealwatch.gamelistservice.model.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameListRepository extends JpaRepository<GameList, Long> {

    GameList findGameListById(Long Id);
    GameList findGameListByUserEmail(String email);
    List<GameList> findAll();
    void deleteGameListById(Long Id);
    void deleteGameListByUserEmail(String email);
    void deleteAll();

}
