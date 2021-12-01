package com.ironhack.authorizationservice.proxy;

import com.ironhack.authorizationservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient("user-service")
public interface UserServiceProxy {

    @GetMapping("/api/v1/users/username={username}")
    @ResponseStatus(HttpStatus.OK)
    User findByUsername(@PathVariable(name = "username") String username);

}
