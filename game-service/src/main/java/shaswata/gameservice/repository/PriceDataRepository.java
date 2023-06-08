package shaswata.gameservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shaswata.gameservice.model.PriceData;

@Repository
public interface PriceDataRepository extends JpaRepository<PriceData, Long> {
    PriceData findPriceDataById(Long Id);
    PriceData deletePriceDataById(Long id);
}
