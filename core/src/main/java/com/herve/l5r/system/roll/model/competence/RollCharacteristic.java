package com.herve.l5r.system.roll.model.competence;

import com.herve.l5r.system.model.Samurai;

import java.util.function.Function;

public interface RollCharacteristic {
    Function<Samurai, Integer> associatedTraitRetriever();

    Function<Samurai, Integer> associatedBonusRetriever();
}
