package com.challenge.backend.service;

import com.challenge.backend.model.User;
import com.challenge.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<String> errors;

    public String register(User user) {
        if(validateEmail(user.getEmail())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "Registered user successfully";
        }else {
            return this.errors.get(0);
        }
    }

    public Boolean validateEmail(String email){
        this.errors = new ArrayList<>();

        Boolean existEmail = existEmail(email);
        String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
                "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
        Boolean validEmail = email.matches(emailPattern);
        Boolean emailNoIsEmpty = !email.isEmpty();

        if(!validEmail){
            this.errors.add("Email invalid");
        } else if(existEmail){
            this.errors.add("Email already exists");
        }
        return  emailNoIsEmpty && validEmail && !existEmail;
    }

    public Boolean existEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
