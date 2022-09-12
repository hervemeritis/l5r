package com.herve.l5r.system.model.competence;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.roll.model.competence.RollCharacteristic;

import java.util.function.Function;

public class IajutsuConcentrationRollCharacteristic implements RollCharacteristic {

    @Override
    public Function<Samurai,Integer> associatedTraitRetriever() {
        return samurai -> samurai.attributs.vide + 1;
    }

    @Override
    public Function<Samurai, Integer> associatedBonusRetriever() {
        return samurai -> samurai.attributs.vide;
    }
}
