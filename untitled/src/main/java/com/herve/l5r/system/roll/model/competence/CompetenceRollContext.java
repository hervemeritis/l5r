package com.herve.l5r.system.roll.model.competence;

import com.herve.l5r.system.model.Competence;
import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.competence.CompetenceTypeRoll;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;

import java.util.Optional;

public class CompetenceRollContext {
    public final CompetenceTypeRoll competenceTypeRoll;
    public final Samurai samurai;

    private CompetenceRollContext(CompetenceTypeRoll competenceTypeRoll, Samurai samurai) {
        this.competenceTypeRoll = competenceTypeRoll;
        this.samurai = samurai;
    }

    public static CompetenceRollContext of(CompetenceTypeRoll typeRoll, Samurai samurai) {
        return new CompetenceRollContext(typeRoll, samurai);
    }

    public RollAndKeepRequest generateRollRequest() {
        return competence().map(competence -> competence.generateRoll(this))
                           .orElseGet(this::defaultRoll);

    }

    public Optional<Competence> competence() {
        return Optional.of(samurai.competences.get(competenceTypeRoll.associatedCompetence));
    }

    public int associatedSamuraiTraitValue() {
        return competenceTypeRoll.characteristic.associatedTraitRetriever().apply(samurai);
    }

    public int associatedSamuraiBonus() {
        return competenceTypeRoll.characteristic.associatedBonusRetriever().apply(samurai);
    }

    private RollAndKeepRequest defaultRoll() {
        return RollAndKeepRequest.builder()
                                 .unkeptDice(0)
                                 .diceToKeep(associatedSamuraiTraitValue())
                                 .modifier(associatedSamuraiBonus())
                                 .explodeOn(RollAndKeepRequest.NON_EXPLODING_VALUE)
                                 .withoutEmphasis();
    }
}
