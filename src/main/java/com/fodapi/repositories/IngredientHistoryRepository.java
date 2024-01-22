package com.fodapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fodapi.models.meals.history.IngredientHistoryEntity;

import java.util.List;

@Repository
public interface IngredientHistoryRepository extends JpaRepository<IngredientHistoryEntity,Long> {
    public List<IngredientHistoryEntity> findAllByIdUser(Long userId);
}
