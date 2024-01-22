package com.fodapi.models.meals.ingredients;

import lombok.Getter;

@Getter
public class IngredientDTO {
    private final Long id;
    private final String name;
    private final double calories;

    public IngredientDTO(IngredientEntity ingredientEntity){
        this.id = ingredientEntity.getId();
        this.name = ingredientEntity.getName();
        this.calories = ingredientEntity.getCalories();
    }
}
