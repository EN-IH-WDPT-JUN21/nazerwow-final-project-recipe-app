package com.ironhack.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                // User Service
                .route(p -> p.path("/api/v1/recipes/**")
                        .uri("lb://recipe-service"))
                .route(p -> p.path("/api/v1/users/**")
                        .uri("lb://user-service"))
                .route(p -> p.path("/api/v1/favourites/**")
                        .uri("lb://favourites-service"))
                .route(p -> p.path("/login/**")
                        .uri("lb://authorization-service"))
                .build();
    }
}
