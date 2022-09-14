package com.herve.l5r.system.model.school.grue.kakita.rank;

import com.herve.l5r.system.model.competence.Competence;
import com.herve.l5r.system.model.competence.CompetenceName;
import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.school.RankSchool;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.competence.CompetenceModifier;
import com.herve.l5r.system.roll.model.competence.CompetenceRollContext;
import com.herve.l5r.system.roll.model.initiative.InitiativeModifier;

import java.util.Optional;

public class LaVoieDeLaGrue implements RankSchool, CompetenceModifier, InitiativeModifier {
    @Override
    public int rank() {
        return 1;
    }

    @Override
    public String name() {
        return "La voie de la grue";
    }

    @Override
    public RollAndKeep generateCompetenceBonusToRollWith(CompetenceRollContext context) {
        return switch (context.competenceTypeRoll) {
            case IAJUTSU_CONCENTRATION, IAJUTSU_FRAPPE -> RollAndKeep.of(0, 1, context.samurai.rank());
            default -> RollAndKeep.zero();
        };
    }

    @Override
    public RollAndKeep generateInitiativeBonus(Samurai samurai) {
        Integer iaijutsuValue = Optional.ofNullable(samurai.competences.get(CompetenceName.IAIJUSTSU))
                                        .map(Competence::value)
                                        .orElse(0);
        return RollAndKeep.of(0, 0, iaijutsuValue * 2);
    }
}
