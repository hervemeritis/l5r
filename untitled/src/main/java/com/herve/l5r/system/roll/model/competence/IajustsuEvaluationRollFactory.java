package com.herve.l5r.system.roll.model.competence;

import com.herve.l5r.system.model.*;

import java.util.function.Function;

public class IajustsuEvaluationRollFactory implements CompetenceRollRequestFactory {
    private static final CompetenceName associatedCompetenceName = CompetenceName.IAJUSTSU;
    private static final Emphasis associatedEmphasis = Emphasis.EVALUATION;

    @Override
    public CompetenceName associatedCompetenceName() {
        return associatedCompetenceName;
    }

    @Override
    public Emphasis associatedEmphasis() {
        return associatedEmphasis;
    }

    @Override
    public Function<Samurai, Integer> associatedTraitRetriever() {
        return samurai -> samurai.attributs.intuition;
    }

    @Override
    public Function<Samurai, Integer> associatedBonusRetriever() {
        return samurai -> 0;
    }
}
