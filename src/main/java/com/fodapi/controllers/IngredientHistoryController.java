package com.fodapi.controllers;

import com.fodapi.components.IngredientHistoryComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fodapi.models.meals.Ingredients.IngredientEntity;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IngredientHistoryController {

    private final IngredientHistoryComponent ingredientHistoryComponent;

    @GetMapping("/IFH") //TODO DOCELOWO ZWRACA list pair Double-IngredientDTO
    public List<Pair<Double, IngredientEntity>> ingredientsFromHistory(){
        //TODO USTALENIE SPOSOBU PRZEKAZANIA USER_ID     przez np.UserDetails
        return ingredientHistoryComponent.getIngredientsFromHistoryByUserId(1L);
    }



}
