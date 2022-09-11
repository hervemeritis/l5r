package com.herve.l5r.system.roll;

import com.herve.l5r.system.model.Competence;
import com.herve.l5r.system.roll.model.RollAndKeep;

public interface CompetenceModifier {
    RollAndKeep generateBonusToRollTo(Competence competence);
}
