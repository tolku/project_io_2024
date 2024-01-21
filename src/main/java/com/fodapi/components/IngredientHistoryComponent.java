package com.fodapi.components;

import com.fodapi.models.meals.History.IngredientHistoryEntity;
import com.fodapi.models.meals.ingredients.IngredientEntity;
import com.fodapi.repositories.IngredientHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;

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

    public List<Pair<Pair<Long, Double>, IngredientEntity>> getIngredientsFromHistoryByUserId(Long userId, Date date){
        List<IngredientHistoryEntity> ingredientHistoryByUserId = getIngredientHistoryByUserId(userId);
        if(ingredientHistoryByUserId==null){
            return null;
        }

        List<IngredientHistoryEntity> listOfIngredientHistory = ingredientHistoryByUserId.stream()
                .filter(elem -> {
                    LocalDate elemDate = elem.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate specifiedDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    return elemDate.equals(specifiedDate);
                })
                .toList();

        List<Pair<Pair<Long, Double>, IngredientEntity>> pairs = listOfIngredientHistory.stream()
                .map(elem -> Pair.of(Pair.of(elem.getId(),elem.getWeight()), ingredientComponent.getIngredientEntityById(elem.getIdIngredient())))
                .toList();

        if(pairs.isEmpty())
            return null;
        return pairs;
    }

    public void addIngredientToHistory(IngredientHistoryEntity ingredientHistoryEntity){
        ingredientHistoryRepository.saveAndFlush(ingredientHistoryEntity);
    }

    public void removeIngredientFromHistory(Long id){
        ingredientHistoryRepository.deleteById(id);
    }
}
