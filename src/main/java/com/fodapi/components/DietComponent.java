package com.fodapi.components;

import com.fodapi.models.diets.DietEntity;
import com.fodapi.repositories.DietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DietComponent {

    private final DietRepository dietRepository;

    public DietEntity getDietByUserId(Long userId){
        return dietRepository.findByUserId(userId);
    }
}
