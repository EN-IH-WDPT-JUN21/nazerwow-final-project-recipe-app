package com.ironhack.userservice.controllers.impl;

import com.ironhack.userservice.dao.User;
import com.ironhack.userservice.dto.CreateUserDTO;
import com.ironhack.userservice.dto.UserDTO;
import com.ironhack.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserControllerImpl implements com.ironhack.userservice.controllers.UserController {
    
    @Autowired
    private UserService userServiceImpl;
    
    
    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll(){
        return userServiceImpl.findAll();
    }

    
    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable(name = "id") Long id){
        return userServiceImpl.findById(id);
    }


    @GetMapping("/username={username}")
    @ResponseStatus(HttpStatus.OK)
    public User findByUsername(@PathVariable(name = "username") String username){
        return userServiceImpl.findByUsername(username);
    }

    
    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(name = "id") Long id){
        userServiceImpl.deleteUser(id);
    }

    
    @Override
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        return userServiceImpl.addUser(createUserDTO);
    }

    
    @Override
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User updateUser(@PathVariable(name = "id") Long id,
                           @RequestBody @Valid UserDTO userDTO){
        return userServiceImpl.updateUser(id, userDTO);
    }
}
