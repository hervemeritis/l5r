package com.herve.l5r.system.roll.model.competence;

import com.herve.l5r.system.model.*;

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
    public int traitValueOf(Samurai samurai) {
        return samurai.attributs.intuition;
    }

    @Override
    public int bonus(Samurai samurai) {
        return 0;
    }
}
