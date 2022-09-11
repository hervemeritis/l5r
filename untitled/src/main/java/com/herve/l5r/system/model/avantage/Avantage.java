package com.herve.l5r.system.model.avantage;

import com.herve.l5r.system.model.Competence;
import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;

public interface Avantage {

    String description();
    String name();
    RollAndKeepRequest enhanceCompetenceRoll(RollAndKeepRequest request, Samurai samurai, Competence competence);


}
