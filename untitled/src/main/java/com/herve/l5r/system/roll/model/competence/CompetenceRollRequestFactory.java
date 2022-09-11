package com.herve.l5r.system.roll.model.competence;

import com.herve.l5r.system.model.*;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;

public interface CompetenceRollRequestFactory {

    CompetenceName associatedCompetenceName();

    Emphasis associatedEmphasis();

    int traitValueOf(Samurai samurai);

    int bonus(Samurai samurai);

    default RollAndKeepRequest generate(Samurai samurai) {
        return samurai.competences.stream()
                                  .filter(competence -> competence.name.equals(associatedCompetenceName()))
                                  .findAny()
                                  .map(competence -> generateRoll(samurai, competence))
                                  .orElseGet(() -> defaultRoll(samurai));
    }

    default RollAndKeepRequest generateRoll(Samurai samurai, Competence competence) {
        return RollAndKeepRequest.builder()
                                 .unkeptDice(competence.value)
                                 .diceToKeep(traitValueOf(samurai))
                                 .explodeOn(10)
                                 .modifier(bonus(samurai))
                                 .emphasis(competence.emphasis.contains(associatedEmphasis()));
    }

    default RollAndKeepRequest defaultRoll(Samurai samurai) {
        return RollAndKeepRequest.builder()
                                 .unkeptDice(traitValueOf(samurai))
                                 .diceToKeep(traitValueOf(samurai))
                                 .explodeOn(RollAndKeepRequest.NON_EXPLODING_VALUE)
                                 .modifier(bonus(samurai))
                                 .withoutEmphasis();
    }
}
