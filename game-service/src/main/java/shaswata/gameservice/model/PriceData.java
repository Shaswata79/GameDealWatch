package shaswata.gameservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Class to store a game's price on a certain date
 */
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
