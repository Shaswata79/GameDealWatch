package gamedealwatch.gameservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@Table(name = "PriceData")
public class PriceData {

    @Id
    @GeneratedValue
    private Long id;

    private double price;

    private LocalDate date;

}
