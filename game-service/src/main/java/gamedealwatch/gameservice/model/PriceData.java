package gamedealwatch.gameservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PriceData")
public class PriceData {

    @Id
    @GeneratedValue
    private Long id;

    private double price;

    private LocalDate date;

    public PriceData(double price, LocalDate date) {
        this.price = price;
        this.date = date;
    }
}
