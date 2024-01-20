package com.example.foodapi.Models.Diet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "diet")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class DietEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private Long userId;
    private double maxCalories;
    private double maxCarbs;
    private double maxFats;
    private double maxProtein;
    private double fiberDemand;
    private double waterDemand;
}
