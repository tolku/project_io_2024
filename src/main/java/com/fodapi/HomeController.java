package com.fodapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fodapi.models.user.Gender;
import com.fodapi.models.user.UserEntity;
import com.fodapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder bcrypt;

    @GetMapping(path = "/")
    public String home() {

        userRepository.saveAndFlush(new UserEntity(2L,"email@s.com","jakishas",12,234.4,175.0, Gender.MALE,true));

        return "home.html";
    }

    @GetMapping(path = "/login")
    public String login() {
        return "login.html";
    }

    @GetMapping(path = "/success")
    public String onSuccess() {
        String encoded = bcrypt.encode("tomcio");
        if (bcrypt.matches("tomcio", encoded)) {
            return "matched.html";
        } else {
            return "success.html";
        }
    }

    @GetMapping(path = "/failure")
    public String onFailure() {
        return "failure.html";
    }
}
