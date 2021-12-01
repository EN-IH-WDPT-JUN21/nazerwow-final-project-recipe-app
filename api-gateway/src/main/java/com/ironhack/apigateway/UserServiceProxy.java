package com.ironhack.apigateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@FeignClient("user-service")
public interface UserServiceProxy {

    @GetMapping("/username={username}")
    @ResponseStatus(HttpStatus.OK)
    User findByUsername(@PathVariable(name = "username") String username);

}
