package ua.malysh.service;

import ua.malysh.domain.Diet;
import ua.malysh.domain.DietType;
import ua.malysh.domain.Physique;

public interface DietCalculatorService {

    Diet getDiet(Physique physiques, DietType dietType);

}