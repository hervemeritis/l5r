package com.herve.l5r.system.roll.model.competence;

import com.herve.l5r.system.roll.model.RollAndKeep;

public interface CompetenceModifier {
    default RollAndKeep generateCompetenceBonusToRollWith(CompetenceRollContext context) {
        return RollAndKeep.zero();
    }
}
