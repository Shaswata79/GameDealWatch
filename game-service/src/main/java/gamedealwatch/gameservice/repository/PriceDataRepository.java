package gamedealwatch.gameservice.repository;

import gamedealwatch.gameservice.model.Item;
import gamedealwatch.gameservice.model.PriceData;
import gamedealwatch.gameservice.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceDataRepository extends JpaRepository<PriceData, Long> {
    PriceData findPriceDataById(Long Id);
    PriceData deletePriceDataById(Long id);
}
