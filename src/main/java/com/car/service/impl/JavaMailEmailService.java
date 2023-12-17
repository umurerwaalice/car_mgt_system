package com.car.service.impl;

import com.car.service.EmailService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class JavaMailEmailService implements EmailService {
    private final String host;
    private final int port;
    private final String username;
    private final String password;

    public JavaMailEmailService(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getInstance(props, authenticator);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
        } catch (MessagingException e) {
            // Handle the exception or log the error
        }

    }


}
