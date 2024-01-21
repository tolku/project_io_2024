package com.fodapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fodapi.models.meals.Ingredients.IngredientEntity;
import com.fodapi.models.meals.Ingredients.Brand.Brand;

@Configuration
public class CustomBeansConfig {

    @Bean
    public IngredientEntity emptyIngredient(){
        return new IngredientEntity(-1L,emptyBrand(),"brak","brak",0.0,0.0,0.0,0.0,0.0,0.0);
    }

    @Bean
    public Brand emptyBrand(){
        return new Brand(-1L,"BrakMarki");
    }
}
