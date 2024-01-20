package com.example.foodapi.Models.Meals.Recipie;

import com.example.foodapi.Models.Meals.Meal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "recipe")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RecipeEntity implements Meal {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String name;
    private String description;
    private String image;
    private Double saturatedFat;
    private Double fiber;

    @Column(nullable = false)
    private double calories;
    @Column(nullable = false)
    private double fat;
    @Column(nullable = false)
    private double protein;
    @Column(nullable = false)
    private double carbs;
    @Column(nullable = false)
    private double fullWeight;

}
