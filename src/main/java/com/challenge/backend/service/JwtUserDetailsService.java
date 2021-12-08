package com.challenge.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (userService.existEmail(email)) {
            com.challenge.backend.model.User persistedUser = userService.findByEmail(email);

            return new User(persistedUser.getEmail(), persistedUser.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}