package shaswata.useraccountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/user-service")
public class UserAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAccountServiceApplication.class, args);
	}

}
