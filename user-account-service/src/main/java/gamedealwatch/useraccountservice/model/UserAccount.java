package gamedealwatch.useraccountservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email"}), name = "User_Account")
public class UserAccount extends Account{

    @Id
    @GeneratedValue
    private Long id;

    private Long listID;

}
