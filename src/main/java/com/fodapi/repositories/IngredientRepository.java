package com.fodapi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.fodapi.models.meals.ingredients.IngredientEntity;

import java.util.Collection;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity,Long> {
    List<IngredientEntity> findAllByIdIn(Collection<Long> id);
    List<IngredientEntity> findAllByNameContainsOrBarcodeContains(String name, String barcode);
    @Query(value = "SELECT * FROM ingredients ORDER BY RANDOM() LIMIT 4", nativeQuery = true)
    List<IngredientEntity> findRandomFour();
}
