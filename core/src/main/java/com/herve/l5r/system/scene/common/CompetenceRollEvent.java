package com.herve.l5r.system.scene.common;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.competence.CompetenceName;
import com.herve.l5r.system.model.competence.CompetenceTypeRoll;
import com.herve.l5r.system.roll.model.ComputedResult;
import com.herve.l5r.system.scene.logger.Event;

public class CompetenceRollEvent implements Event {
    private final Samurai samurai;
    private final ComputedResult computedResult;
    private final CompetenceTypeRoll competenceUsed;

    public CompetenceRollEvent(Samurai samurai, ComputedResult computedResult, CompetenceTypeRoll competenceUsed) {
        this.samurai = samurai;
        this.computedResult = computedResult;
        this.competenceUsed = competenceUsed;
    }

    @Override
    public String message() {
        return samurai.fullName() + " has used " + competenceUsed.name() + "\n -> Competence " + computedResult.toString();
    }
}
