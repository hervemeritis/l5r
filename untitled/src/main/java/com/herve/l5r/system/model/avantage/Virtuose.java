package com.herve.l5r.system.model.avantage;

import com.herve.l5r.system.model.Competence;
import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.roll.CompetenceModifier;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;

public class Virtuose implements Avantage, CompetenceModifier {
    @Override
    public String description() {
        return "Virtuose de son école +1 à toutes les compétences d'école";
    }

    @Override
    public String name() {
        return "Virtuose (12)";
    }


    @Override
    public RollAndKeep generateBonusToRollTo(Competence competence) {
        return competence.schoolCompetence ? RollAndKeep.of(1,0,0) : RollAndKeep.zero();
    }
}
