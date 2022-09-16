package com.herve.l5r.system.roll.model.damage;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.roll.model.RollAndKeep;

public interface DamageModifier {
    RollAndKeep generateBonus(Samurai samurai);
}
