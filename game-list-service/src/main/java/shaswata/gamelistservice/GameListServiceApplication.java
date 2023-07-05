package shaswata.gamelistservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableFeignClients
@RequestMapping("/list")
public class GameListServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameListServiceApplication.class, args);
	}

	/**
	 * @LoadBalanced annotation takes care of decision-making about which instance (of a service) to send the request to. If
	 * not included, this will cause an error since the restTemplate will be confused about which instance the request should be sent.
	 * @return
	 */
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}


}
