package com.herve.l5r.system.model.competence;

import com.herve.l5r.system.model.*;
import com.herve.l5r.system.roll.model.competence.RollCharacteristic;

import java.util.function.Function;

public class IajustsuEvaluationRollCharacteristic implements RollCharacteristic {

    @Override
    public Function<Samurai, Integer> associatedTraitRetriever() {
        return samurai -> samurai.attributs.intuition;
    }

    @Override
    public Function<Samurai, Integer> associatedBonusRetriever() {
        return samurai -> 0;
    }
}
