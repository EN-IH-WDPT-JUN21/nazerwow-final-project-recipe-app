package com.ironhack.apigateway.services.impl;


import com.ironhack.apigateway.dto.UserDTO;
import com.ironhack.apigateway.models.CustomUserDetails;
import com.ironhack.apigateway.proxy.UserServiceProxy;
import com.ironhack.apigateway.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    private UserServiceProxy userServiceProxy;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        final UserDTO user = this.userServiceProxy.findByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found !!");
        } else {
            return new CustomUserDetails(user);
        }
    }
}
