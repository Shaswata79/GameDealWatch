package shaswata.useraccountservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
//import shaswata.amqp.RabbitMQMessageProducer;
//import shaswata.useraccountservice.dto.NotificationRequest;
import shaswata.amqp.RabbitMQMessageProducer;
import shaswata.useraccountservice.dto.NotificationRequest;
import shaswata.useraccountservice.dto.UserDto;
import shaswata.useraccountservice.model.UserAccount;
import shaswata.useraccountservice.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//    private final NotificationClient notificationClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;


    @Transactional
    public UserDto createUser(UserDto dto) throws Exception {
        if(dto.getName() == null || dto.getName() == ""){
            throw new Exception("Name cannot be empty!");
        }
        if(dto.getEmail() == null || dto.getEmail() == ""){
            throw new Exception("Email or username cannot be empty!");
        }

        if(userRepo.findUserAccountByEmail(dto.getEmail()) != null){
            throw new Exception("Account with email '" + dto.getEmail() + "' already exists.");
        }

        UserAccount user = new UserAccount();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setListID(null);

        user = userRepo.save(user);
        // RabbitMQ
        NotificationRequest notificationRequest = new NotificationRequest(user.getId(), user.getEmail(), "Your account has been created!");
        rabbitMQMessageProducer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");
//        Kafka
//        NotificationRequest notificationRequest = new NotificationRequest(user.getId(), user.getEmail(), "Your account has been created!");
//        kafkaTemplate.send("gdwTopic", notificationRequest);
        return UserService.userToDTO(user);

    }


    @Transactional
    public UserDto getUser(String email) throws Exception {
        if(email == null || email == ""){
            throw new Exception("User email cannot be empty!");
        }

        UserAccount user = null;
        user = userRepo.findUserAccountByEmail(email);

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
        dto.setUsername(user.getUsername());
        dto.setId(user.getId());
        dto.setListID(user.getListID());
        return dto;
    }
}
