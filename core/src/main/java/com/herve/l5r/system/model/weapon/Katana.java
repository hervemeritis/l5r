package com.herve.l5r.system.model.weapon;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.competence.CompetenceName;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.damage.Weapon;

public class Katana implements Weapon {
    @Override
    public RollAndKeep baseDamage() {
        return Singleton.BASE_DAMAGE.roll;
    }

    @Override
    public RollAndKeep damageFor(Samurai samurai) {
        RollAndKeep damage = Weapon.super.damageFor(samurai);
        return samurai.retrieveCompetence(CompetenceName.KENJUTSU)
                      .filter(cpt -> cpt.value() >= 3)
                      .map(__ -> damage.add(RollAndKeep.of(1, 0, 0)))
                      .orElse(damage);
    }

    private enum Singleton {
        BASE_DAMAGE(RollAndKeep.of(1, 2, 0));
        private final RollAndKeep roll;

        Singleton(RollAndKeep rollAndKeep) {
            this.roll = rollAndKeep;
        }
    }
}
