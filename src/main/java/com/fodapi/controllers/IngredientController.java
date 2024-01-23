package com.fodapi.controllers;

import com.fodapi.User;
import com.fodapi.components.IngredientComponent;
import com.fodapi.components.UserComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fodapi.models.meals.ingredients.IngredientEntity;

import java.util.List;
import com.fodapi.models.meals.ingredients.IngredientDTO;

@RestController
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientComponent ingredientComponent;

    @GetMapping("/searchIngredients")
    public List<IngredientEntity> searchIngredients(@RequestParam String phrase){
        return ingredientComponent.getIngredientsByPhrase(phrase);
    }

    @GetMapping("/allIngredients")
    public List<IngredientEntity> allIngredients(){
        return ingredientComponent.getAllIngredients();
    }

    @GetMapping("/allDTOs")
    public List<IngredientDTO> allIngredientsDTO(){
        List<IngredientEntity> allIngredients = ingredientComponent.getAllIngredients();
        return allIngredients.stream().map(IngredientDTO::new).toList();
    }

    @GetMapping("/fourDTOs")
    public List<IngredientDTO> fourIngredientsDTO(){
        List<IngredientEntity> allIngredients = ingredientComponent.getFourRandomIngredients();
        return allIngredients.stream().map(IngredientDTO::new).toList();
    }


}
