package gamedealwatch.gamelistservice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Game_List")
public class GameList {

    @Id
    @GeneratedValue
    private Long id;

    private Long userID;

    @ElementCollection
    private List<Long> itemIDs;

}
