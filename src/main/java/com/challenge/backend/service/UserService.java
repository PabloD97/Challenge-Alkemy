package com.challenge.backend.service;

import com.challenge.backend.model.User;
import com.challenge.backend.repository.UserRepository;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SendGrid sendGrid;

    private List<String> errors;

    public String register(User user) {
        if(validateEmail(user.getEmail())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            sendEmailWelcome(user.getEmail());
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

    private Response sendEmailWelcome(String email){
        Mail mail = new Mail(new Email("pablodamiandiazz@gmail.com"), "Welcome", new Email(email),new Content("text/plain", "Welcome to the api of Alkemy challenge "));
        mail.setReplyTo(new Email(email));
        Request request = new Request();

        Response response = null;
        try {

            request.setMethod(Method.POST);

            request.setEndpoint("mail/send");

            request.setBody(mail.build());

            response=this.sendGrid.api(request);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
