package ua.malysh.service.impl;

import ua.malysh.domain.Diet;
import ua.malysh.domain.DietType;
import ua.malysh.domain.Gender;
import ua.malysh.domain.Physique;

public class DietCalculatorService {

    public Diet getDiet(Physique physiques, DietType dietType) {
        if (physiques == null)
            return getDefaultDiet();
        return calculateDiet(physiques, dietType);
    }

    private Diet getDefaultDiet() {
        return new Diet(DietType.MAINTENANCE_WEIGHT,
                1900D,
                950D,
                2000D,
                6500D,
                140D);
    }

    private Diet calculateDiet(Physique physiques, DietType dietType) {
        return calculateMaintenanceDiet(physiques, dietType);
    }

    private Diet calculateMaintenanceDiet(Physique physiques, DietType dietType) {
        var gender = physiques.getGender();
        int age = physiques.getAge();
        double weight = physiques.getWeight();
        double height = physiques.getHight();

        double defaultCalories = (10D * weight) + (6.25 * height) + (5D * age);
        double calories = calculateByGenderAndType(dietType, gender, defaultCalories);
        double water = gender.equals(Gender.FEMALE) ? 31D * weight : 35D * weight;
        double carbohydrates = calories * 0.5;
        double fats = calories * 0.35;
        double proteins = 2D * weight;

        var diet = new Diet();
        diet.setType(DietType.MAINTENANCE_WEIGHT);
        diet.setCalorieNorm(calories);
        diet.setWaterNorm(water);
        diet.setCarbohydrates(carbohydrates);
        diet.setFatNorm(fats);
        diet.setProteinNorm(proteins);

        return diet;
    }

    private double calculateByGenderAndType(DietType dietType, Gender gender, double defaultCalories) {
        double calories = gender.equals(Gender.FEMALE) ? defaultCalories - 161D : defaultCalories + 5D;

        if (dietType.equals(DietType.LOOSE_WEIGHT)) {
            calories = calories * 0.8;
        } else if (dietType.equals(DietType.GAINING_WEIGHT)) {
            calories = calories + 1.2;
        }
        return calories;
    }
}
