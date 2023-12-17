package com.car.controller;

import com.car.service.EmailService;
import com.car.service.impl.JavaMailEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {
    @Value("${email.host}")
    private String host;

    @Value("${email.port}")
    private int port;

    @Value("${email.username}")
    private String username;

    @Value("${email.password}")
    private String password;

    @Bean
    public EmailService emailService() {
        return new JavaMailEmailService(host, port, username, password);
    }
}
