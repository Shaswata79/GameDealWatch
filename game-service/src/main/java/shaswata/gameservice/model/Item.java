package shaswata.gameservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Class to store all the data about a game
 * The combination of the game's name and the store's name is unique
 */
@Entity
@Getter
@Setter
@Table(name = "Item", uniqueConstraints = @UniqueConstraint(columnNames = {"game", "store"}))
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String game;

    private String url;

    @Enumerated(EnumType.STRING)
    private Store store;

    @OneToMany(targetEntity = PriceData.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PriceData> prices;

}
