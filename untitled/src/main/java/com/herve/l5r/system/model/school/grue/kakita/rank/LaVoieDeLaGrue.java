package com.herve.l5r.system.model.school.grue.kakita.rank;

import com.herve.l5r.system.model.school.RankSchool;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.competence.CompetenceModifier;
import com.herve.l5r.system.roll.model.competence.CompetenceRollContext;

public class LaVoieDeLaGrue implements RankSchool, CompetenceModifier {
    @Override
    public int rank() {
        return 1;
    }

    @Override
    public String name() {
        return "La voie de la grue";
    }

    @Override
    public RollAndKeep generateBonusToRollWith(CompetenceRollContext context) {
        return switch (context.competenceTypeRoll) {
            case IAJUTSU_CONCENTRATION, IAJUTSU_FRAPPE -> RollAndKeep.of(0, 1, context.samurai.rank());
            default -> RollAndKeep.zero();
        };
    }
}
