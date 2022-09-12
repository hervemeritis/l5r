package com.herve.l5r.system.model;

import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;
import com.herve.l5r.system.roll.model.competence.CompetenceRollContext;

import java.util.Set;

public class Competence {
    public final CompetenceName name;
    public final int value;
    public final Set<Emphasis> emphasis;

    private final boolean schoolCompetence;

    private Competence(CompetenceName name, int value, Set<Emphasis> emphasis, boolean schoolCompetence) {
        this.name = name;
        this.value = value;
        this.emphasis = emphasis;
        this.schoolCompetence = schoolCompetence;
    }

    public RollAndKeepRequest generateRoll(CompetenceRollContext context) {
        RollAndKeep rollAndKeep = RollAndKeep.of(value, context.associatedSamuraiTraitValue(), context.associatedSamuraiBonus())
                                             .add(context.samurai.generateBonusToRollWith(context));
        return RollAndKeepRequest.builder()
                                 .dicePool(rollAndKeep)
                                 .defaultExplodingDice()
                                 .emphasis(emphasis.contains(context.competenceTypeRoll.emphasis));
    }

    public boolean isSchoolCompetence() {
        return schoolCompetence;
    }

    public static Name builder() {
        return name -> value -> emphasis -> new Competence(name, value, emphasis, false);
    }

    public static Name schoolCompetence() {
        return name -> value -> emphasis -> new Competence(name, value, emphasis, true);
    }

    public interface Name {
        Value name(CompetenceName name);

    }
    public interface Value {
        Specialization value(int value);

    }

    public interface Specialization {
        Competence emphasis(Set<Emphasis> emphasis);
    }
}
