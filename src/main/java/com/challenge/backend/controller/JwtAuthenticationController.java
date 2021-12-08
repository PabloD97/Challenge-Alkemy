package com.challenge.backend.controller;


import com.challenge.backend.model.User;
import com.challenge.backend.security.jwt.JwtRequest;
import com.challenge.backend.security.jwt.JwtResponse;
import com.challenge.backend.security.jwt.JwtTokenUtil;
import com.challenge.backend.service.JwtUserDetailsService;
import com.challenge.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;


    @Autowired
    private UserService userService;

    @PostMapping( "/login")
    @CrossOrigin
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return ResponseEntity.ok(new JwtResponse(token(user(authenticationRequest.getUsername()))));
    }

    private UserDetails user(String userName){
        return userDetailsService.loadUserByUsername(userName);
    }
    private String token(UserDetails userDetails){
        return jwtTokenUtil.generateToken(userDetails);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<String> register(@RequestBody User user) {
        return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
    }
}