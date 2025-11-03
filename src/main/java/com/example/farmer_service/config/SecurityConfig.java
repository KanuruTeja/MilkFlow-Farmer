package com.example.farmer_service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // ✅ new lambda syntax for Spring Security 6+
                .authorizeHttpRequests(auth -> auth
                        // Swagger & OpenAPI endpoints — no authentication
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Auth endpoints — public
                        .requestMatchers("/auth/register", "/auth/login").permitAll()

                        // Role-based access
                        .requestMatchers("/farmer/**").hasRole("FARMER")
                        .requestMatchers("/delivery/**").hasRole("DELIVERY")
                        .requestMatchers("/manager/**").hasRole("MANAGER")
                        .requestMatchers("/customer/**").hasRole("CUSTOMER")

                        // Any other request → authenticated
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
