package shaswata.useraccountservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shaswata.useraccountservice.dto.NotificationRequest;

@SpringBootApplication(
		scanBasePackages = {
				"shaswata.useraccountservice",
				"shaswata.amqp",
		}
)
//@SpringBootApplication(
//		scanBasePackages = {
//				"shaswata.useraccountservice",
//				"shaswata.kafka",
//		}
//)
@RestController
@EnableFeignClients
@RequestMapping("/accounts")
public class UserAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAccountServiceApplication.class, args);
	}

}
