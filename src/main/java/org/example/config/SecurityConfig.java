package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // deshabilitar CSRF para poder realizar las pruebas sin autenticación
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // permitir todos endpoints para hacer tests
                );

        return http.build();
    }
}
