package com.fodapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    PasswordEncoder bcrypt;

    @GetMapping(path = "/")
    public String home() {
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
