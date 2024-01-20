package com.fodapi.models.meals.Recipie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "recipe_ingredients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long ingredientId;
    @Column(nullable = false)
    private Long recipeId;
    @Column(nullable = false)
    private double ingredientAmount;
}
