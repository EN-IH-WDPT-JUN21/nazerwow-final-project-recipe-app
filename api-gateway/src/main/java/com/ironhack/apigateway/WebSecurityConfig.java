package com.ironhack.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .cors().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/login/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/api/v1/recipes/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/api/v1/users/**").permitAll()
                .pathMatchers("/api/v1/favourites").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
                .anyExchange().authenticated()
                .and().build();
    }


}