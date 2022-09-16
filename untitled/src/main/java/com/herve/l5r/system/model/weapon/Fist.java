package com.herve.l5r.system.model.weapon;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.damage.Weapon;

public class Fist implements Weapon {
    @Override
    public RollAndKeep baseDamage() {
        return RollAndKeep.of(-1,1,0);
    }
}
