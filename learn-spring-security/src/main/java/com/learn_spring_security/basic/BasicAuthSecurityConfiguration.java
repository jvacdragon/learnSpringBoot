package com.learn_spring_security.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthSecurityConfiguration {
    @Bean
    SecurityFilterChain securtyFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth -> {
            auth.anyRequest().authenticated();
        });

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.csrf(csrf-> csrf.disable());

        return http.build();
    }
}
