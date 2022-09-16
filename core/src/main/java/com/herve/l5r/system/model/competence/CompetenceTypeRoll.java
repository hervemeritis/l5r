package com.herve.l5r.system.model.competence;

import com.herve.l5r.system.roll.model.competence.RollCharacteristic;

public enum CompetenceTypeRoll {
    IAJUTSU_EVALUATION(CompetenceName.IAIJUSTSU, Emphasis.EVALUATION, new IajustsuEvaluationRollCharacteristic()),
    IAJUTSU_CONCENTRATION(CompetenceName.IAIJUSTSU, Emphasis.CONCENTRATION, new IajutsuConcentrationRollCharacteristic()),
    IAJUTSU_FRAPPE(CompetenceName.IAIJUSTSU, Emphasis.NO_EMPHASIS_KNOWN, new IajutsuFrappeRollCharacteristic());

    public final CompetenceName associatedCompetence;

    public final Emphasis emphasis;

    public final RollCharacteristic characteristic;

    CompetenceTypeRoll(CompetenceName associatedCompetence, Emphasis emphasis, RollCharacteristic characteristic) {
        this.associatedCompetence = associatedCompetence;
        this.emphasis = emphasis;
        this.characteristic = characteristic;
    }
}
