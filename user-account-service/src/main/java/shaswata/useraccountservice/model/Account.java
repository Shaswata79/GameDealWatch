package shaswata.useraccountservice.model;


import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;




@MappedSuperclass
@Getter
@Setter
public abstract class Account {

    private String email;

    private String password;

    private String name;

}
