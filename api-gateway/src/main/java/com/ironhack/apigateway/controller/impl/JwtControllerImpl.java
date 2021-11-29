package com.ironhack.apigateway.controller.impl;

import com.ironhack.apigateway.controller.JwtController;
import com.ironhack.apigateway.models.JwtRequest;
import com.ironhack.apigateway.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/login")
public class JwtControllerImpl implements JwtController {

    @Autowired
    private JwtService jwtService;

    @Override
    @PostMapping()
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.generateToken(jwtRequest);
    }

    @Override
    @GetMapping()
    public String getUsernameFromToken(String token) {
        return jwtService.getUsernameFromToken(token);
    }
}
