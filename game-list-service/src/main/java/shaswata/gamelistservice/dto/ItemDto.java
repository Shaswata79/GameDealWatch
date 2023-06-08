package shaswata.gamelistservice.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

    private Long id;
    private String game;
    private String url;
    private String storeName;
    private Double currentPrice;

}
