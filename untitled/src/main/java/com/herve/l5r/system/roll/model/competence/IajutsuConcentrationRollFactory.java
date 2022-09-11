package com.herve.l5r.system.roll.model.competence;

import com.herve.l5r.system.model.CompetenceName;
import com.herve.l5r.system.model.Emphasis;
import com.herve.l5r.system.model.Samurai;

public class IajutsuConcentrationRollFactory implements CompetenceRollRequestFactory {
    @Override
    public CompetenceName associatedCompetenceName() {
        return CompetenceName.IAJUSTSU;
    }

    @Override
    public Emphasis associatedEmphasis() {
        return Emphasis.CONCENTRATION;
    }

    @Override
    public int traitValueOf(Samurai samurai) {
        return samurai.attributs.vide + 1;
    }

    @Override
    public int bonus(Samurai samurai) {
        return samurai.attributs.vide;
    }
}
