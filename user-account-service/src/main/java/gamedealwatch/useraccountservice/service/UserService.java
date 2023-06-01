package gamedealwatch.useraccountservice.service;

import gamedealwatch.useraccountservice.dto.UserDto;
import gamedealwatch.useraccountservice.model.UserAccount;
import gamedealwatch.useraccountservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    //private final PasswordEncoder passwordEncoder;


    @Transactional
    public UserDto createUser(UserDto dto) throws Exception {
        if(dto.getName() == null || dto.getName() == ""){
            throw new Exception("Name cannot be empty!");
        }
        if(dto.getEmail() == null || dto.getEmail() == "" || dto.getPassword() == null || dto.getPassword() == ""){
            throw new Exception("Email or password cannot be empty!");
        }

        if(userRepo.findUserAccountByEmail(dto.getEmail()) != null){
            throw new Exception("Account with email '" + dto.getEmail() + "' already exists.");
        }

        UserAccount user = new UserAccount();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setListID(null);

        userRepo.save(user);
        return UserService.userToDTO(user);

    }



    @Transactional
    public UserDto getUser(String email) throws Exception {
        if(email == null || email == ""){
            throw new Exception("User email cannot be empty!");
        }

        UserAccount user = null;
//        if(currentUser.getUsername().equals(email)){
            user = userRepo.findUserAccountByEmail(email);
//        } else{
//            throw new Exception("You can only view your own account details!");
//        }

        if(user == null){
            throw new Exception("No user account exists with email " + email);
        }

        return UserService.userToDTO(user);
    }



    @Transactional
    public UserDto updateList(String email, Long id) throws Exception {
        UserAccount user = userRepo.findUserAccountByEmail(email);
        if(user == null){
            throw new Exception("User account with email '" + email + "' not found.");
        }
        if(user.getListID() != null){
            throw new Exception("User with email '" + email + "' already has a game list!");
        }

        user.setListID(id);
        userRepo.save(user);
        return UserService.userToDTO(user);

    }


    static UserDto userToDTO(UserAccount user) {
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setPassword(user.getPassword());
        dto.setId(user.getId());
        dto.setListID(user.getListID());
        return dto;
    }
}
