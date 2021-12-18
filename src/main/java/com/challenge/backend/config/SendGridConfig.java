package com.challenge.backend.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendGridConfig {

    @Value("${sendgrid.key}")
    public String key;

    @Bean
    public SendGrid getSendGrind(){
        return new SendGrid(key);
    }
}
