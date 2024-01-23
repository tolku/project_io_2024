package com.fodapi.servies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSenderImpl mailSender;

    public void sendRegistrationConfirmationEmail(String to) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setFrom("projektfodapi@gmail.com");
        msg.setSubject("Registration confirmation email");
        msg.setText("Hi, an account for this email has just been registered, you can now use Fodapi web application!");
        mailSender.send(msg);

    }
}
