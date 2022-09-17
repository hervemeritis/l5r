package com.herve.l5r.system.model.avantage;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.competence.Competence;
import com.herve.l5r.system.roll.model.competence.CompetenceModifier;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.competence.CompetenceRollContext;

public class Virtuose implements AvantageDefinition, CompetenceModifier {
    @Override
    public String description() {
        return "Virtuose de son école +1 à toutes les compétences d'école";
    }

    @Override
    public String name() {
        return "Virtuose (12)";
    }

    @Override
    public int cost(Samurai samurai) {
        return 12;
    }


    @Override
    public RollAndKeep generateCompetenceBonusToRollWith(CompetenceRollContext context) {
        return context.competence()
                      .filter(Competence::isSchoolCompetence)
                      .map(__ -> RollAndKeep.of(1, 0, 0))
                      .orElseGet(RollAndKeep::zero);
    }
}
