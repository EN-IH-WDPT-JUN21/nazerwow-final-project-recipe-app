package com.ironhack.apigateway.dto;

import com.ironhack.apigateway.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String name;
    private String username;
    private String password;
    @Email
    private String email;
    private String location;
    private String bio;
    private String pictureUrl;
    private List<Role> roles;

}