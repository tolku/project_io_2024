package com.fodapi.controllers;

import com.fodapi.User;
import com.fodapi.components.IngredientHistoryComponent;
import com.fodapi.components.UserComponent;
import com.fodapi.models.meals.History.IngredientHistoryEntity;
import com.fodapi.models.meals.ingredients.IngredientEntity;
import lombok.RequiredArgsConstructor;

import org.antlr.v4.runtime.misc.Triple;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class IngredientHistoryController {

    private final IngredientHistoryComponent ingredientHistoryComponent;
    private final UserComponent userComponent;

   @GetMapping("/diary")  ///diary?date=2024-01-21
    public List<Triple<Long, Double, IngredientEntity>> ingredientsFromHistory(
            @RequestParam String date,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long id = userComponent.getUserByMail(userDetails.getUsername()).getId();
        LocalDate localDate = LocalDate.parse(date);
        Date utilDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return ingredientHistoryComponent.getIngredientsFromHistoryByUserId(id,utilDate);
    }

    @GetMapping("/addIngredient")
    public void addIngredientToHistory(@RequestParam Long ingredientId, @RequestParam double amount ,@AuthenticationPrincipal UserDetails userDetails){
        Long id = userComponent.getUserByMail(userDetails.getUsername()).getId();
        IngredientHistoryEntity historyRecord = new IngredientHistoryEntity(null, id, amount, ingredientId, null, new Date(), "Jakiś opis");
        ingredientHistoryComponent.addIngredientToHistory(historyRecord);
    }

    @GetMapping("/remIngredient")
    public void removeIngredientFromHistory(@RequestParam Long historyRecord){
        ingredientHistoryComponent.removeIngredientFromHistory(historyRecord);
    }

}
