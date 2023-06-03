package shaswata.gameservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/game-service")
public class GameServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameServiceApplication.class, args);
	}

}
