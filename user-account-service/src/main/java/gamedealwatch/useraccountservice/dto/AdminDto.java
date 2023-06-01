package gamedealwatch.useraccountservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {

    private Long id;
    private String name;
    private String email;
    private String password;

}
