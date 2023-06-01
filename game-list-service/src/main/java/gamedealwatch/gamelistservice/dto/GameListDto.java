package gamedealwatch.gamelistservice.dto;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameListDto {

    private Long id;
    private String userEmail;
    private List<ListItemDto> items;

}
