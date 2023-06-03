package shaswata.useraccountservice.repository;

import shaswata.useraccountservice.model.AdminAccount;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdminRepository extends JpaRepository<AdminAccount, Long> {

    AdminAccount findAdminAccountByEmail(String email);
    List<AdminAccount> findAll();
    void deleteAdminAccountByEmail(String email);
    void deleteAll();

}
