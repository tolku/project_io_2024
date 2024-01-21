package com.fodapi;

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

    @GetMapping(path = "/")
    public String home() {


        return "home.html";
    }
}
