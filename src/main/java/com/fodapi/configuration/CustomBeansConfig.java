package com.fodapi.configuration;

import com.fodapi.models.diets.DietEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fodapi.models.meals.ingredients.IngredientEntity;
import com.fodapi.models.meals.ingredients.brand.Brand;

@Configuration
public class CustomBeansConfig {

    @Bean
    public IngredientEntity emptyIngredient(){
        return new IngredientEntity(-1L,emptyBrand(),"brak","brak",0.0,0.0,0.0,0.0,0.0,0.0);
    }

    @Bean
    public Brand emptyBrand(){
        return new Brand(-1L,"Brak Marki");
    }

    @Bean
    public DietEntity emptyDiet(){
        return new DietEntity(-1L,-1L,3000,300,100,150,20,3500);
    }

}
