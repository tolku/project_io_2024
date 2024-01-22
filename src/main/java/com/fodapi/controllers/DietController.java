package com.fodapi.controllers;

import com.fodapi.components.DietComponent;
import com.fodapi.models.diets.DietEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DietController {

    private final DietComponent dietComponent;
    private final DietEntity emptyDiet;

    @GetMapping("/diet")
    public DietEntity getDiet(/*@AuthenticationPrincipal UserDetails userDetails*/){
        DietEntity dietByUserId = dietComponent.getDietByUserId(1L);
        if(dietByUserId==null)
            return emptyDiet;

        return dietByUserId;
    }
}
