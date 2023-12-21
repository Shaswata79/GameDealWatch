package shaswata.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity  //Spring Cloud Gateway is built on Spring Boot 2.x, Spring WebFlux so need to use @EnableWebFluxSecurity instead of @EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity){
        serverHttpSecurity.authorizeExchange(exchanges ->
                                exchanges.pathMatchers("/game/user/getItems", "/game/user/getItem", "/accounts/user/updateListID").denyAll()     // internal access only
                                        .pathMatchers("/game/user/**").permitAll()                  // game-service
                                        .pathMatchers("/game/store/**").authenticated()             // game-service
                                        .pathMatchers("/list/admin/**").hasRole("ADMIN")            // game-list-service
                                        .pathMatchers("/list/user/**").hasRole("USER")              // game-list-service
                                        .pathMatchers("/accounts/admin/**").hasRole("ADMIN")        // user-account-service
                                        .pathMatchers("/accounts/user/create").authenticated()          // user-account-service
                                        .pathMatchers("/accounts/user/**").hasRole("USER")          // user-account-service
                )
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(extractGrantedAuthorities())));      // use custom role converter
        serverHttpSecurity.csrf(csrfSpec -> csrfSpec.disable());
        return serverHttpSecurity.build();
    }


    private Converter<Jwt, Mono<AbstractAuthenticationToken>> extractGrantedAuthorities() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new RoleConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
