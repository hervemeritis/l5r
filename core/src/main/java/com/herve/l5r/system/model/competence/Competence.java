package com.herve.l5r.system.model.competence;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;
import com.herve.l5r.system.roll.model.competence.CompetenceRollContext;

import java.util.Set;

public class Competence {
    public final CompetenceName name;
    private int value;

    public final Set<Emphasis> emphasis;
    private final boolean schoolCompetence;

    private final Samurai samurai;

    private Competence(CompetenceName name, int value, Set<Emphasis> emphasis, boolean schoolCompetence, Samurai samurai) {
        this.name = name;
        this.value = value;
        this.emphasis = emphasis;
        this.schoolCompetence = schoolCompetence;
        this.samurai = samurai;
    }

    public RollAndKeepRequest generateRollRequest(CompetenceRollContext context) {
        RollAndKeep rollAndKeep = generateRoll(context);
        return RollAndKeepRequest.builder()
                                 .dicePool(rollAndKeep)
                                 .defaultExplodingDice()
                                 .emphasis(emphasis.contains(context.competenceTypeRoll.emphasis));
    }

    public RollAndKeep generateRoll(CompetenceRollContext context) {
        CompetenceTypeRoll typeRoll = context.competenceTypeRoll;
        Integer traitValue = typeRoll.characteristic.associatedTraitRetriever().apply(samurai);
        Integer bonusValue = typeRoll.characteristic.associatedBonusRetriever().apply(samurai);
        return RollAndKeep.of(value, traitValue, bonusValue)
                          .add(samurai.generateCompetenceBonusToRollWith(context))
                          .add(context.temporaryBonus());
    }

    public boolean isSchoolCompetence() {
        return schoolCompetence;
    }

    public int value() {
        return value;
    }

    public void incrementValue() {
        incrementValue(1);
    }

    public void incrementValue(int increment) {
        value += increment;
    }

    public static Name builder() {
        return name -> value -> emphasis -> samurai -> new Competence(name, value, emphasis, false, samurai);
    }

    public static Name schoolCompetence() {
        return name -> value -> emphasis -> samurai -> new Competence(name, value, emphasis, true, samurai);
    }

    public interface Name {
        Value name(CompetenceName name);

    }

    public interface Value {
        Specialization value(int value);

    }

    public interface Specialization {
        LinkedSamurai emphasis(Set<Emphasis> emphasis);
    }

    public interface LinkedSamurai {
        Competence samurai(Samurai samurai);
    }
}
