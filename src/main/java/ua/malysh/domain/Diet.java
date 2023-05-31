package ua.malysh.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diet {

    @Column(name = "diet_type")
    @Enumerated(EnumType.STRING)
    private DietType type;

    @Column(name = "diet_calorie")
    private Double calorieNorm;

    @Column(name = "diet_carbohydrates")
    private Double carbohydrates;

    @Column(name = "diet_water")
    private Double waterNorm;

    @Column(name = "diet_fat")
    private Double fatNorm;

    @Column(name = "diet_protein")
    private Double proteinNorm;
}
