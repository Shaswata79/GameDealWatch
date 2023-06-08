package shaswata.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

//@SpringBootApplication(scanBasePackages = {
//		"shaswata.notificationservice",
//		"shaswata.amqp",
//})
@SpringBootApplication(scanBasePackages = {
		"shaswata.notificationservice",
		"shaswata.kafka",
})
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
