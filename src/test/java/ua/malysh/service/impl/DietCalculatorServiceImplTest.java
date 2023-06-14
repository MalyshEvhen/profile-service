package ua.malysh.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.malysh.domain.ActivityLevel;
import ua.malysh.domain.Diet;
import ua.malysh.domain.DietType;
import ua.malysh.domain.Gender;
import ua.malysh.domain.Physique;
import ua.malysh.service.DietCalculatorService;

class DietCalculatorServiceImplTest {
    private DietCalculatorService service;

    private Diet defaultDiet;

    @BeforeEach
    void setUp() {
        service = new DietCalculatorServiceImpl();

        defaultDiet = new Diet(DietType.MAINTENANCE_WEIGHT,
                1900D,
                950D,
                2000D,
                650D,
                140D);
    }

    @Test
    void shouldReturnDefaultDietIfPhysicsNull() {
        var actual = service.getDiet(null, null);
        assertEquals(defaultDiet, actual);
    }

    @Test
    void shouldCalculateDietForMaintenanceType() {
        var physique = new Physique();
        physique.setActivityLevel(ActivityLevel.LOW_ACTIVE);
        physique.setAge(30);
        physique.setGender(Gender.FEMALE);
        physique.setHight(165D);
        physique.setWeight(65D);

        var dietType = DietType.MAINTENANCE_WEIGHT;

        // Diet(type=MAINTENANCE_WEIGHT, calorieNorm=1884.09, carbohydrates=261.26, waterNorm=2015.0, fatNorm=52.33, proteinNorm=70.65)

        var expected = new Diet(DietType.MAINTENANCE_WEIGHT,
                1884.09,
                261.26,
                2015.0,
                52.33,
                70.65);
        var actual = service.getDiet(physique, dietType);

        assertEquals(expected, actual);
    }
}
