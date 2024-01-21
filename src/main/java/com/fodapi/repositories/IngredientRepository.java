package com.fodapi.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fodapi.models.meals.Ingredients.IngredientEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity,Long> {
    List<IngredientEntity> findAllByIdIn(Collection<Long> id);
    List<IngredientEntity> findAllByNameContainsOrBarcodeContains(String name, String barcode);

}
