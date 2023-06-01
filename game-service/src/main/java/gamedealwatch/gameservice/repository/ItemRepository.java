package gamedealwatch.gameservice.repository;

import gamedealwatch.gameservice.model.Item;
import gamedealwatch.gameservice.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findItemsByStore(Store store);
    List<Item> findItemsByGame(String game);
    Item findItemById(Long Id);
    Item findItemByStoreAndGame(Store store, String game);
    Item deleteItemById(Long id);
}
