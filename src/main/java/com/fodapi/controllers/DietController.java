package com.fodapi.controllers;

import com.fodapi.User;
import com.fodapi.components.DietComponent;
import com.fodapi.components.UserComponent;
import com.fodapi.models.diets.DietEntity;
import com.fodapi.models.user.Gender;
import com.fodapi.models.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DietController {

    private final DietComponent dietComponent;
    private final UserComponent userComponent;
    private final DietEntity emptyDiet;

    @GetMapping("/diet")
    public DietEntity getDiet(@AuthenticationPrincipal UserDetails userDetails){
        Long id = userComponent.getUserByMail(userDetails.getUsername()).getId();
        DietEntity dietByUserId = dietComponent.getDietByUserId(id);
        if(dietByUserId==null)
            return emptyDiet;

        return dietByUserId;
    }

    @GetMapping("/addDiet")
    public void addDiet(@AuthenticationPrincipal UserDetails userDetails,
                        @RequestParam Double activityLevel,
                        @RequestParam Integer age,
                        @RequestParam Gender gender,
                        @RequestParam Double height,
                        @RequestParam Double weight){

        UserEntity user = userComponent.getUserByMail(userDetails.getUsername());
        Long userId = user.getId();
        user.setAge(age);
        user.setGender(gender);
        user.setHeight(height);
        user.setWeight(weight);
        userComponent.saveUser(user);

        if(age==null || gender==null || height==null || weight==null || activityLevel==null){
            DietEntity dietEmp = emptyDiet.clone();
            dietEmp.setId(null);
            dietEmp.setUserId(userId);
            dietComponent.addDiet(emptyDiet);
        }else {
            DietEntity diet = dietComponent.computeDiet(userId,age,gender,height,weight,activityLevel);
            dietComponent.addDiet(diet);
        }
    }

}
