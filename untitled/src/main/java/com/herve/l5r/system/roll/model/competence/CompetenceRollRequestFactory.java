package com.herve.l5r.system.roll.model.competence;

import com.herve.l5r.system.model.*;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;

import java.util.Optional;
import java.util.function.Function;

public interface CompetenceRollRequestFactory {

    CompetenceName associatedCompetenceName();

    Emphasis associatedEmphasis();

    Function<Samurai, Integer> associatedTraitRetriever();

    Function<Samurai, Integer> associatedBonusRetriever();

    default RollAndKeepRequest generate(Samurai samurai) {
        return Optional.ofNullable(samurai.competences.get(associatedCompetenceName()))
                       .map(cpt -> generateRoll(samurai, cpt))
                       .orElseGet(() -> defaultRoll(samurai));

    }

    default RollAndKeepRequest generateRoll(Samurai samurai, Competence competence) {
        RollAndKeep rollAndKeep = RollAndKeep.of(competence.value, associatedTraitRetriever().apply(samurai), associatedBonusRetriever().apply(samurai))
                                             .add(samurai.generateBonusToRollTo(competence));
        return RollAndKeepRequest.builder()
                                 .dicePool(rollAndKeep)
                                 .defaultExplodingDice()
                                 .emphasis(competence.emphasis.contains(associatedEmphasis()));
    }

    default RollAndKeepRequest defaultRoll(Samurai samurai) {
        Integer trait = associatedTraitRetriever().apply(samurai);
        return RollAndKeepRequest.builder()
                                 .unkeptDice(trait)
                                 .diceToKeep(trait)
                                 .modifier(associatedBonusRetriever().apply(samurai))
                                 .explodeOn(RollAndKeepRequest.NON_EXPLODING_VALUE)
                                 .withoutEmphasis();
    }
}
