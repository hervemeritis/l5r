package com.herve.l5r.system.roll.model.initiative;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.roll.model.RollAndKeep;

public interface InitiativeModifier {
    RollAndKeep generateInitiativeBonus(Samurai samurai);
}
