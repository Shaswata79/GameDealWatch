package gamedealwatch.gameservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemDto {

    private Long id;
    private String game;
    private String url;
    private String storeName;
    private Double currentPrice;

}
