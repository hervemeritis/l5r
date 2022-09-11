package com.herve.l5r.system.roll.model.competence;

import com.herve.l5r.system.model.CompetenceName;
import com.herve.l5r.system.model.Emphasis;
import com.herve.l5r.system.model.Samurai;

import java.util.function.Function;

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
    public Function<Samurai,Integer> associatedTraitRetriever() {
        return samurai -> samurai.attributs.vide + 1;
    }

    @Override
    public Function<Samurai, Integer> associatedBonusRetriever() {
        return samurai -> samurai.attributs.vide;
    }
}
