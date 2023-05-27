package gamedealwatch.gameservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Store")
public class Store {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
