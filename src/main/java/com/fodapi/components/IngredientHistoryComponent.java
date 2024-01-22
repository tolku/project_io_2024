package com.fodapi.components;

import com.fodapi.models.meals.history.IngredientHistoryEntity;
import com.fodapi.models.meals.ingredients.IngredientEntity;
import com.fodapi.repositories.IngredientHistoryRepository;
import lombok.RequiredArgsConstructor;

import org.antlr.v4.runtime.misc.Triple;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;
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

    public List<Triple<Long, Double, IngredientEntity>> getIngredientsFromHistoryByUserId(Long userId, Date date){
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

        List<Triple<Long, Double, IngredientEntity>> triples = listOfIngredientHistory.stream()
                .map(elem -> new Triple<>(
                        elem.getId(),
                        elem.getWeight(),
                        ingredientComponent.getIngredientEntityById(elem.getIdIngredient())))
                .collect(Collectors.toList());

        if(triples.isEmpty())
            return null;
        return triples;
    }

    public void addIngredientToHistory(IngredientHistoryEntity ingredientHistoryEntity){
        ingredientHistoryRepository.saveAndFlush(ingredientHistoryEntity);
    }

    public void removeIngredientFromHistory(Long id){
        ingredientHistoryRepository.deleteById(id);
    }
}
