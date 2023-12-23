package shaswata.gameservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PriceDateDto {
    private Double price;
    private LocalDate date;
}
