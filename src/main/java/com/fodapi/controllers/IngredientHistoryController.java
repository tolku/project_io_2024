package com.fodapi.controllers;

import com.fodapi.components.IngredientHistoryComponent;
import com.fodapi.models.meals.history.IngredientHistoryEntity;
import com.fodapi.models.meals.ingredients.IngredientEntity;
import lombok.RequiredArgsConstructor;

import org.antlr.v4.runtime.misc.Triple;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class IngredientHistoryController {

    private final IngredientHistoryComponent ingredientHistoryComponent;


    @GetMapping("/diary")
    public List<Triple<Long, Double, IngredientEntity>> ingredientsFromHistory(/*@RequestParam Date date, @AuthenticationPrincipal UserDetails userDetails*/ ){
        //TODO USTALENIE SPOSOBU PRZEKAZANIA USER_ID     przez np.UserDetails
        Date date = new Date();
        return ingredientHistoryComponent.getIngredientsFromHistoryByUserId(1L,date);
    }

    @GetMapping("/addIngredient")                      //Mozna dodac param Description
    public void addIngredientToHistory(@RequestParam Long ingredientId,@RequestParam double amount  /*,@AuthenticationPrincipal UserDetails userDetails*/){
        IngredientHistoryEntity historyRecord = new IngredientHistoryEntity(null, 1L, amount, ingredientId, null, new Date(), "Jakiś opis");
        ingredientHistoryComponent.addIngredientToHistory(historyRecord);
    }

    @GetMapping("/remIngredient")
    public void removeIngredientFromHistory(@RequestParam Long historyRecord/*,@AuthenticationPrincipal UserDetails userDetails*/){
        ingredientHistoryComponent.removeIngredientFromHistory(historyRecord);
    }

}
