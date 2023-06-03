package shaswata.gamelistservice.repository;

import shaswata.gamelistservice.model.GameList;
import shaswata.gamelistservice.model.ListItem;
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
