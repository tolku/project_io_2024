package com.fodapi.models.meals.ingredients.brand;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
}
