package com.herve.l5r.system.scene.combat.model;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.competence.CompetenceName;
import com.herve.l5r.system.roll.model.ComputedResult;
import com.herve.l5r.system.scene.logger.Event;

import java.util.Arrays;

public class CombatEvent implements Event {
    public final Samurai samurai;
    public final FrappeResult frappeResult;
    public final CompetenceName competenceUsed;

    public CombatEvent(Samurai samurai, FrappeResult frappeResult, CompetenceName competenceUsed) {
        this.samurai = samurai;
        this.frappeResult = frappeResult;
        this.competenceUsed = competenceUsed;
    }

    @Override
    public String message() {
        String frappeMessage = samurai.fullName() + " used " + competenceUsed.name() + " to hit at difficulty " + frappeResult.difficulty()
                + "\n -> Hit " + frappeResult.hitResult();
        if(frappeResult.hasSucceed()) {
            frappeMessage = frappeMessage + "\n -> Damage " + frappeResult.damageResult();
        }
        return frappeMessage;
    }
}
