package com.herve.l5r.system.model.school.lion.matsu.rank;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.school.RankSchool;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.damage.DamageModifier;

public class HurlementDuLion implements RankSchool, DamageModifier {
    @Override
    public int rank() {
        return 1;
    }

    @Override
    public String name() {
        return "Le hurlement du Lion";
    }

    @Override
    public RollAndKeep generateDamageBonus(Samurai samurai) {
        return RollAndKeep.of(0,0, (int) samurai.honneur);
    }
}
