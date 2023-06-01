package gamedealwatch.useraccountservice.service;

import gamedealwatch.useraccountservice.dto.AdminDto;
import gamedealwatch.useraccountservice.dto.UserDto;
import gamedealwatch.useraccountservice.model.AdminAccount;
import gamedealwatch.useraccountservice.model.UserAccount;
import gamedealwatch.useraccountservice.repository.AdminRepository;
import gamedealwatch.useraccountservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepo;
    private final AdminRepository adminRepo;
    //private final PasswordEncoder passwordEncoder;


    @Transactional
    public List<UserDto> getAllUsers(){
        List<UserAccount> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream()
                .map(UserService::userToDTO)
                .collect(Collectors.toList());

        return userDtos;
    }


    @Transactional
    public UserDto getUser(String email){
        UserAccount user = userRepo.findUserAccountByEmail(email);
        return UserService.userToDTO(user);
    }



    @Transactional
    public AdminDto createAdmin(AdminDto dto) throws Exception {
        if(dto.getName() == null || dto.getName() == ""){
            throw new Exception("Name cannot be empty!");
        }
        if(dto.getEmail() == null || dto.getEmail() == "" || dto.getPassword() == null || dto.getPassword() == ""){
            throw new Exception("Email or password cannot be empty!");
        }

        if(adminRepo.findAdminAccountByEmail(dto.getEmail()) != null){
            throw new Exception("Account with email '" + dto.getEmail() + "' already exists.");
        }

        AdminAccount admin = new AdminAccount();
        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());

        adminRepo.save(admin);
        return AdminService.adminToDTO(admin);
    }



    static AdminDto adminToDTO(AdminAccount user) {
        AdminDto dto = new AdminDto();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setPassword(user.getPassword());
        dto.setId(user.getId());
        return dto;
    }


}
