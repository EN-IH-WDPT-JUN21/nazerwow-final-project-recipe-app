package com.ironhack.userservice.services;

import com.ironhack.userservice.dao.User;
import com.ironhack.userservice.dto.CreateUserDTO;
import com.ironhack.userservice.dto.UserDTO;
import com.ironhack.userservice.enums.Role;
import com.ironhack.userservice.repositories.UserRepository;
import com.ironhack.userservice.services.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;

    private User user1;
    private User user2;
    private List<User> userList;

    @BeforeEach
    void setUp() {
        user1 = new User(
                "TestUser1",
                "username1",
                "password1",
                List.of(Role.USER),
                "test@email.1",
                "testLocation1",
                "testBio1",
                "testUrl1"
        );
        user2 = new User(
                "TestUser2",
                "username2",
                "password2",
                List.of(Role.USER),
                "test@email.2",
                "testLocation2",
                "testBio2",
                "testUrl2"
        );
        userList = List.of(user1, user2);
        userRepository.saveAll(userList);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findAll_Valid() {
        var repoSize = userServiceImpl.findAll().size();
        assertEquals(userList.size(), repoSize);
    }

    @Test
    void findById_Valid() {
        var foundUser = userServiceImpl.findById(user1.getId());
        assertEquals(user1.getName(), foundUser.getName());
    }

    @Test
    void findById_Invalid_ThrowsException() {
        assertThrows(ResponseStatusException.class, () -> userServiceImpl.findById(user1.getId() - 50L));
    }

    @Test
    void deleteUser() {
        var userListBefore = userServiceImpl.findAll().size();
        userServiceImpl.deleteUser(user1.getId());
        var userListAfter = userServiceImpl.findAll().size();
        assertEquals(userListBefore - 1, userListAfter);
    }

    @Test
    void deleteUser_ThrowsException() {
        assertThrows(ResponseStatusException.class, () ->  userServiceImpl.deleteUser(user1.getId() - 60L));
    }

    @Test
    void deleteUser_ThrowsException2() {
        userServiceImpl.deleteUser(user1.getId());
        assertThrows(ResponseStatusException.class, () ->  userServiceImpl.deleteUser(user1.getId()));
    }

    @Test
    void addUser_Valid_DatabaseIncrease() {
        CreateUserDTO createUserDTO = new CreateUserDTO(
                "newUser",
                "newUsername",
                "newPassword",
                "new@email.com",
                "newLocation",
                "newBio",
                "newUrl"
        );
        var userListBefore = userServiceImpl.findAll().size();
        User user = userServiceImpl.addUser(createUserDTO);
        var userListAfter = userServiceImpl.findAll().size();
        assertEquals(userListBefore + 1, userListAfter);
    }

    @Test
    void addUser_Valid_CheckValues() {
        CreateUserDTO createUserDTO = new CreateUserDTO(
                "newUser",
                "newUsername",
                "newPassword",
                "new@email.com",
                "newLocation",
                "newBio",
                "newUrl"
        );
        User user = userServiceImpl.addUser(createUserDTO);
        assertEquals(createUserDTO.getName(), user.getName());
        assertEquals(createUserDTO.getUsername(), user.getUsername());
        assertEquals(createUserDTO.getPassword(), user.getPassword());
        assertEquals(createUserDTO.getEmail(), user.getEmail());
        assertEquals(createUserDTO.getLocation(), user.getLocation());
        assertEquals(createUserDTO.getLocation(), user.getLocation());
        assertEquals(createUserDTO.getBio(), user.getBio());
        assertEquals(LocalDate.now().getDayOfMonth(), user.getCreatedDate().getDayOfMonth());
        assertEquals(LocalDate.now().getDayOfMonth(), user.getEditedDate().getDayOfMonth());
    }

    @Test
    void updateUser_Valid_NoDataBaseIncrease() {
        UserDTO userDTO = new UserDTO(
                "newUser",
                "newUsername",
                "newPassword",
                "new@email.com",
                "newLocation",
                "newBio",
                "newUrl"
        );
        var userListBefore = userServiceImpl.findAll().size();
        User user = userServiceImpl.updateUser(user1.getId(), userDTO);
        var userListAfter = userServiceImpl.findAll().size();
        assertEquals(userListBefore, userListAfter);
    }

    @Test
    void updateUser_Valid_TestUpdated() {
        user1.setCreatedDate(LocalDate.now().minusYears(50L));
        userRepository.save(user1);
        System.out.println(user1.getCreatedDate());
        UserDTO userDTO = new UserDTO(
                "newUser",
                "newUsername",
                "newPassword",
                "new@email.com",
                "newLocation",
                "newBio",
                "newUrl"
        );
        User updatedUser = userServiceImpl.updateUser(user1.getId(), userDTO);
        assertEquals(userDTO.getName(), updatedUser.getName());
        assertEquals(userDTO.getUsername(), updatedUser.getUsername());
        assertEquals(userDTO.getPassword(), updatedUser.getPassword());
        assertEquals(userDTO.getEmail(), updatedUser.getEmail());
        assertEquals(userDTO.getLocation(), updatedUser.getLocation());
        assertEquals(userDTO.getLocation(), updatedUser.getLocation());
        assertEquals(userDTO.getBio(), updatedUser.getBio());
        assertNotEquals(updatedUser.getCreatedDate().getYear(), updatedUser.getEditedDate().getYear());
    }

    @Test
    void updateUser_Valid_TestNullFilter() {
        user1.setCreatedDate(LocalDate.now().minusYears(50L));
        userRepository.save(user1);
        User originalUser = userServiceImpl.findById(user1.getId());
        UserDTO userDTO = new UserDTO(
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        userServiceImpl.updateUser(user1.getId(), userDTO);
        User updatedUser = userServiceImpl.findById(user1.getId());
        assertEquals(originalUser.getName(), updatedUser.getName());
        assertEquals(originalUser.getUsername(), updatedUser.getUsername());
        assertEquals(originalUser.getPassword(), updatedUser.getPassword());
        assertEquals(originalUser.getEmail(), updatedUser.getEmail());
        assertEquals(originalUser.getLocation(), updatedUser.getLocation());
        assertEquals(originalUser.getLocation(), updatedUser.getLocation());
        assertEquals(originalUser.getBio(), updatedUser.getBio());
        assertNotEquals(updatedUser.getCreatedDate().getYear(), updatedUser.getEditedDate().getYear());
    }

    @Test
    void updateUser_ThrowsException() {
        UserDTO userDTO = new UserDTO(
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        assertThrows(ResponseStatusException.class, () ->  userServiceImpl.updateUser(user1.getId() - 60L,
                userDTO));
    }
}