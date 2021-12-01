package com.ironhack.authorizationservice.services;


import com.ironhack.authorizationservice.model.CustomUserDetails;
import com.ironhack.authorizationservice.model.User;
import com.ironhack.authorizationservice.proxy.UserServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceProxy userServiceProxy;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        final User user = userServiceProxy.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found !!");
        } else {
            return new CustomUserDetails(user);
        }
    }
}
