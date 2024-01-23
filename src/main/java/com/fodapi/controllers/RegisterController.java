package com.fodapi;

import com.fodapi.models.diets.DietEntity;
import com.fodapi.models.user.UserEntity;
import com.fodapi.repositories.DietRepository;
import com.fodapi.repositories.UserRepository;
import com.fodapi.servies.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @GetMapping("/register")
    public String getRegistrationViewName() {
        return "register.html";
    }

    //TODO change User to UserEntityDTO
    @PostMapping("/register")
    void processRegistration(User user) {
        userRepository.saveAndFlush(new UserEntity(user.getUsername(),passwordEncoder.encode(user.getPassword()),true));
        emailService.sendRegistrationConfirmationEmail(user.getUsername());
    }
}