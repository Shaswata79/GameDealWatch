package shaswata.gamelistservice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Game_List", uniqueConstraints = @UniqueConstraint(columnNames = {"userEmail"}))
public class GameList {

    @Id
    @GeneratedValue
    private Long id;

    private String userEmail;
    private String listName;

    @OneToMany(targetEntity = ListItem.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ListItem> items;

}
