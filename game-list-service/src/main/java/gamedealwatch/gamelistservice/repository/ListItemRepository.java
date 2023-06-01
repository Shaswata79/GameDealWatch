package gamedealwatch.gamelistservice.repository;

import gamedealwatch.gamelistservice.model.GameList;
import gamedealwatch.gamelistservice.model.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ListItemRepository extends JpaRepository<ListItem, Long> {

    GameList findListItemById(Long Id);
    List<ListItem> findAll();
    void deleteListItemById(Long id);
    void deleteAll();

}
