package shaswata.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.Collections;


@Configuration
@EnableWebFluxSecurity  //Spring Cloud Gateway is built on Spring Boot 2.x, Spring WebFlux so need to use @EnableWebFluxSecurity instead of @EnableWebSecurity
public class SecurityConfig {

    /**
     * Bean responsible for filtering requests to endpoints based on roles
     * Uses custom role converter to get the correct roles associated to a request's JWT
     * @param serverHttpSecurity
     * @return
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity){

        serverHttpSecurity.cors().configurationSource(corsConfiguration()).and().csrf(csrfSpec -> csrfSpec.disable());;

        serverHttpSecurity.authorizeExchange(exchanges ->
                                exchanges.pathMatchers("/game/user/getItems", "/game/user/getItem", "/accounts/user/updateListID").denyAll()     // internal access only
                                        .pathMatchers("/game/user/**").authenticated()                  // game-service
                                        .pathMatchers("/game/store/**").authenticated()             // game-service
                                        .pathMatchers("/list/admin/**").hasRole("ADMIN")            // game-list-service
                                        .pathMatchers("/list/user/**").hasRole("USER")              // game-list-service
                                        .pathMatchers("/accounts/admin/**").hasRole("ADMIN")        // user-account-service
                                        .pathMatchers("/accounts/user/create").authenticated()      // user-account-service
                                        .pathMatchers("/accounts/user/**").hasRole("USER")          // user-account-service
                )
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(extractGrantedAuthorities())));      // use custom role converter

        return serverHttpSecurity.build();
    }


    /**
     * Create custom CORS configuration
     * For microservices, can only specify allowed origins once. Since the allowed origins is being specified here, we cannot use "@CrossOrigin(origins = "*")" in other microservices' RestController
     * @return
     */
    @Bean
    public CorsConfigurationSource corsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }


    private Converter<Jwt, Mono<AbstractAuthenticationToken>> extractGrantedAuthorities() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new RoleConverter());
        System.out.println("Here");
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
