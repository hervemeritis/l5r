package com.herve.l5r.system.model.avantage;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.damage.DamageModifier;

public class Grand implements AvantageDefinition, DamageModifier {
    @Override
    public String description() {
        return "Le personnage est plus grand que la normale et gagne +1G0 aux jets de dommage";
    }

    @Override
    public String name() {
        return "Grand";
    }

    @Override
    public int cost(Samurai samurai) {
        return 4;
    }

    @Override
    public RollAndKeep generateDamageBonus(Samurai samurai) {
        return RollAndKeep.of(1,0,0);
    }
}
