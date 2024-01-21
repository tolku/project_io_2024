package com.fodapi.components;

import java.util.List;
import java.util.Optional;

import com.fodapi.models.meals.Ingredients.IngredientEntity;
import com.fodapi.repositories.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IngredientComponent {

    private final IngredientRepository ingredientRepository;
    private final IngredientEntity emptyIngredient;

    public IngredientEntity getIngredientEntityById(Long id){
        Optional<IngredientEntity> byId = ingredientRepository.findById(id);
        return byId.orElse(emptyIngredient);
    }

    public List<IngredientEntity> getIngredientsInIds(List<Long> ids){
        return ingredientRepository.findAllByIdIn(ids);
    }

    public List<IngredientEntity> getIngredientsByPhrase(String phrase){
        return ingredientRepository.findAllByNameContainsOrBarcodeContains(phrase,phrase);
    }

}
