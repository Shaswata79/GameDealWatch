package gamedealwatch.useraccountservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email"}), name = "Admin_Account")
public class AdminAccount extends Account{

    @Id
    @GeneratedValue
    private Long id;


}
