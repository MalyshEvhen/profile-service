package ua.malysh.domain;

public enum DietType {
    WO_DIET(0.0),
    LOOSE_WEIGHT(0.8),
    GAINING_WEIGHT(1.2),
    MAINTENANCE_WEIGHT(1.0);

    private final double typeCoefficient;

    private DietType(double coefficient) {
        this.typeCoefficient = coefficient;
    }

    public double getTypeCoefficient() {
        return typeCoefficient;
    }
}
