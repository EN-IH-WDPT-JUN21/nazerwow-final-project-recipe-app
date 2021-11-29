package com.ironhack.apigateway.proxy;

import com.ironhack.apigateway.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient("user-service")
public interface UserServiceProxy {

    @GetMapping("/username={username}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO findByUsername(@PathVariable(name = "username") String username);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO findById(@PathVariable(name = "id") Long id);
}
