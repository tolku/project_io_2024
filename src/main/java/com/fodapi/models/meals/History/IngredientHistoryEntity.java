package com.example.foodapi.Models.Meals.History;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity(name = "ingredients_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientHistoryEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(nullable = false)
    private Long idUser;
    @Column(nullable = false)
    private double weight;
    private Long idIngredient;
    private Long idRecipe;
    private Date time;
    private String description;

}
