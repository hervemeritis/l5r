package com.herve.l5r.system.model.avantage;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.school.TypeSchool;

public class ForceDeLaTerre implements AvantageDefinition {
    @Override
    public String description() {
        return "Les pénalités de bléssures sont réduites de 3";
    }

    @Override
    public String name() {
        return "Force de la Terre";
    }

    @Override
    public int cost(Samurai samurai) {
        return samurai.knownFamilySchool()
                      .stream()
                      .anyMatch(familySchool -> familySchool.type().equals(TypeSchool.BUSHI)) ? 2 : 3;
    }
}
