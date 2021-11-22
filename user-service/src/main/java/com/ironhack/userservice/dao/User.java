package com.ironhack.userservice.dao;

import com.ironhack.userservice.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends Users {

    @Email
    private String email;
    private String location;
    private String bio;
    private String pictureUrl;

    public User(String name, String username, String password, List<Role> role, String email, String location, String bio, String pictureUrl) {
        super(name, username, password, role);
        this.email = email;
        this.location = location;
        this.bio = bio;
        this.pictureUrl = pictureUrl;
    }
}