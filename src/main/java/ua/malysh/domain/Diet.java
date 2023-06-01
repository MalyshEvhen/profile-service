package ua.malysh.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
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

    public static Diet getDefault() {
        return Diet.builder()
            .type(DietType.WO_DIET)
            .calorieNorm(2000D)
            .carbohydrates(0D)
            .waterNorm(2000D)
            .fatNorm(0D)
            .proteinNorm(0D)
            .build();
    }
}
