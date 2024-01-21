package com.fodapi.components;

import com.fodapi.models.meals.History.IngredientHistoryEntity;
import com.fodapi.models.meals.Ingredients.IngredientEntity;
import com.fodapi.repositories.IngredientHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class IngredientHistoryComponent {

    private final IngredientHistoryRepository ingredientHistoryRepository;
    private final IngredientComponent ingredientComponent;

    public List<IngredientHistoryEntity> getIngredientHistoryByUserId(Long userId){
        List<IngredientHistoryEntity> allByIdUser = ingredientHistoryRepository.findAllByIdUser(userId);
        if(allByIdUser.isEmpty())
            return null;
        else return allByIdUser;
    }

    public List<Pair<Double, IngredientEntity>> getIngredientsFromHistoryByUserId(Long userId){
        List<IngredientHistoryEntity> ingredientHistoryByUserId = getIngredientHistoryByUserId(userId);
        if(ingredientHistoryByUserId==null){
            return null;
        }

        List<Pair<Double, IngredientEntity>> pairs = ingredientHistoryByUserId.stream()
                .map(elem -> Pair.of(elem.getWeight(), ingredientComponent.getIngredientEntityById(elem.getIdIngredient())))
                .toList();

        if(pairs.isEmpty())
            return null;
        return pairs;
    }

    public void addIngredientToHistory(IngredientHistoryEntity ingredientHistoryEntity){
        ingredientHistoryRepository.saveAndFlush(ingredientHistoryEntity);
    }

}
