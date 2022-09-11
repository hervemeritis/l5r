package com.herve.l5r.system.model.avantage;

import com.herve.l5r.system.model.Competence;
import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;

public class Virtuose implements Avantage {
    @Override
    public String description() {
        return "Virtuose de son école +1 à toutes les compétences d'école";
    }

    @Override
    public String name() {
        return "Virtuose (12)";
    }

    @Override
    public RollAndKeepRequest enhanceCompetenceRoll(RollAndKeepRequest request, Samurai samurai, Competence competence) {

        return null;
    }
}
