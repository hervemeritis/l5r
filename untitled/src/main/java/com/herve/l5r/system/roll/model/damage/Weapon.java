package com.herve.l5r.system.roll.model.damage;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;

public interface Weapon {

    RollAndKeep baseDamage();

    default RollAndKeep damageFor(Samurai samurai) {
        return baseDamage().add(RollAndKeep.of(samurai.attributs.force,0,0));
    }

    default RollAndKeepRequest generateDamageRoll(Samurai samurai, int nd) {
        RollAndKeep damageRoll = this.damageFor(samurai).add(RollAndKeep.of(nd, 0 , 0));
        return RollAndKeepRequest.builder()
                                 .dicePool(damageRoll)
                                 .defaultExplodingDice()
                                 .withoutEmphasis();
    }
}
