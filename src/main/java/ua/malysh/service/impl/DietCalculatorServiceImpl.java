package ua.malysh.service.impl;

import org.springframework.stereotype.Service;

import ua.malysh.domain.Diet;
import ua.malysh.domain.DietType;
import ua.malysh.domain.Gender;
import ua.malysh.domain.Physique;
import ua.malysh.service.DietCalculatorService;

@Service
public class DietCalculatorServiceImpl implements DietCalculatorService {
    private static final double MALE_WATER_COF = 35D;
    private static final double FEMALE_WATER_COF = 31D;
    private static final double PROTEINS_ENERGY_COST = 4;
    private static final double FATS_ENERGY_COST = 9;
    private static final double CARBOHYDRATES_ENERGY_COST = 3.75;
    private static final double PROTEINS_DAILY_NORM = 0.15;
    private static final double FATS_DAILY_NORM = 0.25;
    private static final double CARBOHYDRATES_DAILY_NORM = 0.52;

    @Override
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
                650D,
                140D);
    }

    private Diet calculateDiet(Physique physiques, DietType dietType) {
        return calculateMaintenanceDiet(physiques, dietType);
    }

    private Diet calculateMaintenanceDiet(Physique physiques, DietType dietType) {
        var gender = physiques.getGender();
        double weight = physiques.getWeight();
        double activityCoefficient = physiques.getActivityLevel().getActivityCoefficient();
        double dietTypeCoefficient = dietType.getTypeCoefficient();

        double calories = getBasalMetabolismRate(physiques) * dietTypeCoefficient * activityCoefficient;
        double water = gender.equals(Gender.FEMALE) ? FEMALE_WATER_COF * weight : MALE_WATER_COF * weight;
        double carbohydrates = (calories / CARBOHYDRATES_ENERGY_COST) * CARBOHYDRATES_DAILY_NORM;
        double fats = (calories / FATS_ENERGY_COST) * FATS_DAILY_NORM;
        double proteins = (calories / PROTEINS_ENERGY_COST) * PROTEINS_DAILY_NORM;

        var diet = new Diet();
        diet.setType(DietType.MAINTENANCE_WEIGHT);
        diet.setCalorieNorm(round(calories));
        diet.setWaterNorm(round(water));
        diet.setCarbohydrates(round(carbohydrates));
        diet.setFatNorm(round(fats));
        diet.setProteinNorm(round(proteins));

        return diet;
    }

    /**
     * Mifflin-St Jeor Equation:
     * For men:
     * BMR = 10W + 6.25H - 5A + 5
     *
     * For women:
     * BMR = 10W + 6.25H - 5A - 161
     */

    private double getBasalMetabolismRate(Physique physiques) {
        var gender = physiques.getGender();
        int age = physiques.getAge();
        double weight = physiques.getWeight();
        double height = physiques.getHight();
        double genderCof = gender.equals(Gender.FEMALE) ? -161D : 5D;

        return genderCof + (10D * weight) + (6.25 * height) - (5D * age);
    }

    private double round(double value) {
        double val = value;
        val = val * 100;
        val = (int) val;
        val = val / 100;

        return val;
    }
}
