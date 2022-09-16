package com.herve.l5r.system.model.weapon;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;
import com.herve.l5r.system.roll.model.damage.Weapon;

public enum WeaponType implements Weapon {
    KATANA(new Katana()),
    FIST(new Fist());

    private final Weapon weapon;

    WeaponType(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public RollAndKeep baseDamage() {
        return weapon.baseDamage();
    }

    @Override
    public RollAndKeep damageFor(Samurai samurai) {
        return weapon.damageFor(samurai);
    }

    @Override
    public RollAndKeepRequest generateDamageRoll(Samurai samurai, int nd) {
        return weapon.generateDamageRoll(samurai, nd);
    }
}
