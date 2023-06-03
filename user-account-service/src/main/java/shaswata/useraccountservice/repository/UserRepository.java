package shaswata.useraccountservice.repository;

import shaswata.useraccountservice.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, String> {

    UserAccount findUserAccountByEmail(String email);
    List<UserAccount> findAll();
    void deleteUserAccountByEmail(String email);
    void deleteAll();

}