package com.challenge.backend.controller;

import com.challenge.backend.model.User;
import com.challenge.backend.security.jwt.JwtRequest;
import com.challenge.backend.security.jwt.JwtTokenUtil;
import com.challenge.backend.service.JwtUserDetailsService;
import com.challenge.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private JwtUserDetailsService userDetailsService;

    @MockBean
    private AuthenticationManager authenticationManager;


    @MockBean
    private JwtTokenUtil jwtTokenUtil;


    @MockBean
    UserService userService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void it_should_return_created_user() throws Exception {
        User user = new User();
        user.setPassword("string");
        user.setEmail("pablo@gmail.com");

        when(userService.register(user)).thenReturn("Registered user successfully");

        mockMvc.perform(post("/auth/register")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void it_should_return_email_exist() throws Exception {
        User user = new User();
        user.setPassword("string");
        user.setEmail("pablo@gmail.com");

        when(userService.register(user)).thenReturn("Registered user successfully");

        mockMvc.perform(post("/auth/register")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        User user2 = new User();
        user.setPassword("string");
        user.setEmail("pablo@gmail.com");

        when(userService.register(user2)).thenReturn("Email already exists");

        mockMvc.perform(post("/auth/register")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void it_should_return_email_invalid() throws Exception {
        User user = new User();
        user.setPassword("string");
        user.setEmail("pablogmail.com");

        when(userService.register(user)).thenReturn("Email invalid" +
                "");

        mockMvc.perform(post("/auth/register")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void it_should_validate_the_token() throws Exception {
        JwtRequest authenticationRequest = new JwtRequest();
        authenticationRequest.setUsername("pablo@gmail.com");
        authenticationRequest.setPassword("string");
        org.springframework.security.core.userdetails.User userSpring = new org.springframework.security.core.userdetails.User("nel@gmail.com", "12345678", new ArrayList<>());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(authenticationRequest );
        given(userDetailsService.loadUserByUsername(authenticationRequest.getUsername())).willReturn( userSpring);
        given(jwtTokenUtil.generateToken(userSpring)).willReturn("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZWxAZ21haWwuY29tIiwiZXhwIjoxNjMzMzAzOTYxLCJpYXQiOjE2MzMyODU5NjF9.ERdjG6KoM5Z6VIEy7I48ZaHToJ7-dyrEutDl2hUQfjnpKZLDxqLFGQ3vf30Lb1gv-3fNz0bXYtLmTdYs7L5q6A");
        mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isOk());
        verify(userDetailsService, VerificationModeFactory.times(1)).loadUserByUsername(authenticationRequest.getUsername());
        reset(userDetailsService);
        verify(jwtTokenUtil, VerificationModeFactory.times(1)).generateToken( userSpring);
        reset(userDetailsService);
    }
}
