package com.ironhack.apigateway;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private String name;
    private String username;
    private String password;
    private String email;
    private String location;
    private String bio;
    private String pictureUrl;
    private List<Role> roles;

}
