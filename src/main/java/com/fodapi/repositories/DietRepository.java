package com.fodapi.repositories;

import com.fodapi.models.diets.DietEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietRepository extends JpaRepository<DietEntity, Long> {
    DietEntity findByUserId(Long userId);
}
