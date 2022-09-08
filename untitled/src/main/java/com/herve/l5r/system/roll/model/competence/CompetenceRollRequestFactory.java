package com.herve.l5r.system.roll.model.competence;

import com.herve.l5r.system.model.*;

public interface CompetenceRollRequestFactory {

    CompetenceName associatedCompetenceName();

    Emphasis associatedEmphasis();

    int traitValueOf(Samurai samurai);

    default RollAndKeepRequest generate(Samurai samurai) {
        return samurai.competences.stream()
                                  .filter(competence -> competence.name.equals(associatedCompetenceName()))
                                  .findAny()
                                  .map(competence -> generateRoll(samurai, competence))
                                  .orElseGet(() -> defaultRoll(samurai));
    }

    default RollAndKeepRequest generateRoll(Samurai samurai, Competence competence) {
        return RollAndKeepRequest.withEmphasis(competence.emphasis.contains(associatedEmphasis()))
                                 .unkeptDice(competence.value)
                                 .diceToKeep(traitValueOf(samurai))
                                 .explodeOn(10)
                                 .withoutModifier();
    }

    default RollAndKeepRequest defaultRoll(Samurai samurai) {
        return RollAndKeepRequest.withoutEmphasis()
                                 .unkeptDice(traitValueOf(samurai))
                                 .diceToKeep(traitValueOf(samurai))
                                 .explodeOn(RollAndKeepRequest.NON_EXPLODING_VALUE)
                                 .withoutModifier();
    }
}
