package com.ironhack.userservice.controllers;

import com.ironhack.userservice.dao.User;
import com.ironhack.userservice.dto.CreateUserDTO;
import com.ironhack.userservice.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface UserController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<User> findAll();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    User findById(@PathVariable(name = "id") Long id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(@PathVariable(name = "id") Long id);

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    User addUser(@Valid @RequestBody CreateUserDTO createUserDTO);

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    User updateUser(@PathVariable(name = "id") Long id,
                    @RequestBody @Valid UserDTO userDTO);
}
