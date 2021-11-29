package com.ironhack.apigateway.services;

import com.ironhack.apigateway.models.JwtRequest;
import org.springframework.http.ResponseEntity;

public interface JwtService {
    ResponseEntity<?> generateToken(JwtRequest jwtRequest) throws Exception;

    String getUsernameFromToken(String token);
}
