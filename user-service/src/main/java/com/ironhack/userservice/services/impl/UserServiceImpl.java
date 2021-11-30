package com.ironhack.userservice.services.impl;

import com.ironhack.userservice.dao.User;
import com.ironhack.userservice.dto.CreateUserDTO;
import com.ironhack.userservice.dto.UserDTO;
import com.ironhack.userservice.enums.Role;
import com.ironhack.userservice.repositories.UserRepository;
import com.ironhack.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No User found with ID: " + id);
        return user.get();
    }

    @Override
    public void deleteUser(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    @Override
    public User addUser(CreateUserDTO createUserDTO){
        return userRepository.save(convertNewUserDTOToUser(createUserDTO));
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO){
        User user = findById(id);
        return userRepository.save(updateUserWithUserDTO(userDTO, user));
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No User found with ID: " + username);
        return user.get();
    }

    private User updateUserWithUserDTO(UserDTO userDTO, User user) {
        if (userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }
        if (userDTO.getUsername() != null) {
            user.setUsername(userDTO.getUsername());
        }
        if (userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getLocation() != null) {
            user.setLocation(userDTO.getLocation());
        }
        if (userDTO.getBio() != null) {
            user.setBio(userDTO.getBio());
        }
        if (userDTO.getPictureUrl() != null) {
            user.setPictureUrl(userDTO.getPictureUrl());
        }
        user.setEditedDate(LocalDate.now());
        return user;
    }

    private User convertNewUserDTOToUser(CreateUserDTO createUserDTO) {
        return new User(
                createUserDTO.getName(),
                createUserDTO.getUsername(),
                createUserDTO.getPassword(),
                List.of(Role.USER),
                createUserDTO.getEmail(),
                createUserDTO.getLocation(),
                createUserDTO.getBio(),
                createUserDTO.getPictureUrl()
        );
    }
}
