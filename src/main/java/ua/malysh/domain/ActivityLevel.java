package ua.malysh.domain;

public enum ActivityLevel {
    SEDENTARY(1.2),
    LOW_ACTIVE(1.375),
    ACTIVE(1.55),
    VERY_ACTIVE(1.725),
    PRO(1.9);

    private Double activityCoefficient;

    private ActivityLevel(Double coefficient) {
        this.activityCoefficient = coefficient;
    }

    public Double getActivityCoefficient() {
        return activityCoefficient;
    }
}
