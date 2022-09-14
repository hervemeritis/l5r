package com.herve.l5r.system.roll.model.competence;

import com.herve.l5r.system.model.competence.Competence;
import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.competence.CompetenceTypeRoll;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;
import com.herve.l5r.system.roll.model.RollContext;

import java.util.Optional;

public class CompetenceRollContext extends RollContext {
    public final CompetenceTypeRoll competenceTypeRoll;
    private RollAndKeep temporaryBonus;

    private CompetenceRollContext(CompetenceTypeRoll competenceTypeRoll, Samurai samurai) {
        super(samurai);
        this.competenceTypeRoll = competenceTypeRoll;
        this.temporaryBonus = RollAndKeep.zero();
    }

    public static CompetenceRollContext of(CompetenceTypeRoll typeRoll, Samurai samurai) {
        return new CompetenceRollContext(typeRoll, samurai);
    }

    public CompetenceRollContext withTemporaryBonus(RollAndKeep temporaryBonus) {
        this.temporaryBonus = temporaryBonus;
        return this;
    }

    public RollAndKeepRequest generateCompetenceRollRequest() {
        return competence().map(competence -> competence.generateRoll(this))
                           .orElseGet(this::defaultRoll);

    }

    public Optional<Competence> competence() {
        return Optional.of(samurai.competences.get(competenceTypeRoll.associatedCompetence));
    }

    private int associatedSamuraiTraitValue() {
        return competenceTypeRoll.characteristic.associatedTraitRetriever().apply(samurai);
    }

    private int associatedSamuraiBonus() {
        return competenceTypeRoll.characteristic.associatedBonusRetriever().apply(samurai);
    }

    private RollAndKeepRequest defaultRoll() {
        RollAndKeep roll = RollAndKeep.of(0, associatedSamuraiTraitValue(), associatedSamuraiBonus())
                                      .add(temporaryBonus);
        return RollAndKeepRequest.builder()
                                 .dicePool(roll)
                                 .explodeOn(RollAndKeepRequest.NON_EXPLODING_VALUE)
                                 .withoutEmphasis();
    }

    public RollAndKeep temporaryBonus() {
        return temporaryBonus;
    }
}
