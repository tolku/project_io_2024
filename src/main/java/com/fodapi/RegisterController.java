package com.fodapi;

import com.fodapi.models.user.Gender;
import com.fodapi.models.user.UserEntity;
import com.fodapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/register")
    public String getRegistrationViewName() {
        return "register.html";
    }

    @PostMapping("/register")
    public String processRegistration(User user) {
        userRepository.saveAndFlush(new UserEntity(user.getEmail(),passwordEncoder.encode(user.getPassword()),true));
        return "qwertycz.html";
    }
}