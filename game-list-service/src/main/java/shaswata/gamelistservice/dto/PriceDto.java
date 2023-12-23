package shaswata.gamelistservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceDto {

    private Long id;
    private String game;
    private Double currentPrice;

}
