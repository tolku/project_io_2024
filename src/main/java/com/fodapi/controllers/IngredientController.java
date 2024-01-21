package com.fodapi.controllers;

import com.fodapi.components.IngredientComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fodapi.models.meals.Ingredients.IngredientEntity;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientComponent ingredientComponent;

    @GetMapping("/SI")  //TODO DOCELOWO ZWRACA IngredientDTO list
    public List<IngredientEntity> searchIngredients(/*@RequestParam String phrase*/){
        String phrase = "ie";
        return ingredientComponent.getIngredientsByPhrase(phrase);
    }
}
