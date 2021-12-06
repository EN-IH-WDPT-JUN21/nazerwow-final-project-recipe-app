package com.ironhack.recipeservice.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/api/v1/recipes/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/api/v1/cuisines/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/api/v1/diets/**").permitAll()
                .mvcMatchers(HttpMethod.GET, "/api/v1/measurements/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer().jwt();
    }

}
