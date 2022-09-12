package com.herve.l5r.system.roll.model.competence;

import com.herve.l5r.system.roll.model.RollAndKeep;

public interface CompetenceModifier {
    default RollAndKeep generateBonusToRollWith(CompetenceRollContext context) {
        return RollAndKeep.zero();
    }
}
