package com.ironhack.apigateway.controller;

import com.ironhack.apigateway.models.JwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface JwtController {
    @PostMapping()
    ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception;

    @GetMapping()
    String getUsernameFromToken(String token);
}
