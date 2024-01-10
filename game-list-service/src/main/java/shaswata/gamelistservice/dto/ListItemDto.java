package shaswata.gamelistservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListItemDto {

    private Long itemID;
    private String gameName;
    private double threshold;

}
