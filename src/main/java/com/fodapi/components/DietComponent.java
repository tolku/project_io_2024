package com.fodapi.components;

import com.fodapi.models.diets.DietEntity;
import com.fodapi.models.user.Gender;
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

    public void addDiet(DietEntity dietEntity){
        dietRepository.saveAndFlush(dietEntity);
    }

    public DietEntity computeDiet(long userId, int age, Gender gender, double height, double weight, Double activityLevel) {
        DietEntity dietEntity = new DietEntity();

        double bmr = calculateBMR(gender, weight, height, age);
        double totalCalories = bmr * activityLevel;

        dietEntity.setUserId(userId);
        dietEntity.setMaxCalories(totalCalories);
        dietEntity.setMaxProtein((totalCalories * 0.3) / 4);
        dietEntity.setMaxFats((totalCalories * 0.3) / 9);
        dietEntity.setMaxCarbs((totalCalories * 0.4) / 4);
        dietEntity.setFiberDemand(calculateFiberDemand(weight));
        dietEntity.setWaterDemand(weight * 35);

        return dietEntity;
    }

    private double calculateFiberDemand(double weight) {
        double fiberPerThousandCalories = 14;
        double averageCalories = 2000;
        return (fiberPerThousandCalories / 1000) * averageCalories;
    }

    private double calculateBMR(Gender gender, double weight, double height, int age) {
        if (gender == Gender.MALE) {
            return 10 * weight + 6.25 * height - 5 * age + 5;
        } else if (gender == Gender.FEMALE) {
            return 10 * weight + 6.25 * height - 5 * age - 161;
        } else {
            return (10 * weight + 6.25 * height - 5 * age + 5 + 10 * weight + 6.25 * height - 5 * age - 161) / 2;
        }
    }
}
