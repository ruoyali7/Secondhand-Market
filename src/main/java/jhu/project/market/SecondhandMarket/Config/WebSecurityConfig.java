package jhu.project.market.SecondhandMarket.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .requiresChannel(requiresChannel ->
                        requiresChannel
                                .anyRequest().requiresSecure()  // Enforce HTTPS for all requests
                )
                .csrf(csrf -> csrf.disable());  // Disable CSRF for testing

        return http.build();
    }
}
