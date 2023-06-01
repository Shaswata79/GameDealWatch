package gamedealwatch.gamelistservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ListItem {


    @Id
    @GeneratedValue
    private Long id;

    private Long itemID;

    private double threshold;

}