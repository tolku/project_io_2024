package com.fodapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fodapi.models.user.Gender;
import com.fodapi.models.user.UserEntity;
import com.fodapi.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder bcrypt;

    @GetMapping(path = "/")
    public String home() {
        userRepository.saveAndFlush(new UserEntity(2L,"ZMIANA@s.com","jakishas",12,234.4,175.0, Gender.MALE,true));
        return "home.html";
    }

    @GetMapping(path = "/success")
    public String onSuccess(Model modelMap) {
            modelMap.addAttribute("message", "THIS IS THE ADDED ATRIBUTE TO MODEL MAP");
            return "matched.html";
    }


    @GetMapping(path = "/failure")
    public String onFailure() {
        return "failure.html";
    }
}
