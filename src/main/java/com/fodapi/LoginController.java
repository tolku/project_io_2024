package com.fodapi;

import com.fodapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/loginn")
    public String signIn(SimpleUser user) {
        if (passwordEncoder.matches(user.getPassword(), userRepository.findByEmail(user.getEmail()).getPasswordHash())){
            return "qwertycz.html";
        }
        return "failure.html";
    }

    @GetMapping("/login")
    public String siii() {
        return "login";
    }
}
