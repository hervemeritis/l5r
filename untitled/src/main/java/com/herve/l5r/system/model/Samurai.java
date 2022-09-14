package com.herve.l5r.system.model;

import com.herve.l5r.system.model.avantage.Avantage;
import com.herve.l5r.system.model.competence.Competence;
import com.herve.l5r.system.model.competence.CompetenceName;
import com.herve.l5r.system.model.school.School;
import com.herve.l5r.system.roll.RollAndKeepDiceSystemFactory;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;
import com.herve.l5r.system.roll.model.competence.CompetenceModifier;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.competence.CompetenceRollContext;
import com.herve.l5r.system.roll.model.initiative.InitiativeModifier;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Samurai implements CompetenceModifier {
    public final Family family;
    public final String name;
    public final Attribut attributs;
    public final double honneur;
    public final double gloire;
    public final double statut;
    public final Map<CompetenceName, Competence> competences;

    public final Set<Avantage> avantages;

    public final List<School> schools;

    Samurai(SamuraiBuilder builder) {
        this.family = builder.family;
        this.name = builder.name;
        this.attributs = builder.attributs;
        this.honneur = builder.honneur;
        this.gloire = builder.gloire;
        this.statut = builder.statut;
        this.competences = builder.competences.stream()
                .map(cptBuilder -> cptBuilder.samurai(this))
                                              .collect(() -> new EnumMap<>(CompetenceName.class), (map, cpt) -> map.put(cpt.name, cpt), EnumMap::putAll);
        this.avantages = Set.copyOf(builder.avantages);
        this.schools = builder.schools;
    }

    public static SamuraiBuilder builder() {
        return new SamuraiBuilder();
    }

    @Override
    public RollAndKeep generateCompetenceBonusToRollWith(CompetenceRollContext context) {
        return Stream.concat(avantages.stream().map(Avantage::definition), schools.stream().flatMap(School::availableRank))
                     .filter(CompetenceModifier.class::isInstance)
                     .map(CompetenceModifier.class::cast)
                     .map(cptModifier -> cptModifier.generateCompetenceBonusToRollWith(context))
                     .reduce(RollAndKeep.zero(), RollAndKeep::add);
    }

    public int rank() {
        int reputation = computeReputationFromCompetence() + computeReputationFromRing();
        return reputation > 149 ? (reputation - 150) / 25 + 2 : 1;
    }

    private int computeReputationFromCompetence() {
        return competences.values()
                          .stream()
                          .mapToInt(Competence::value)
                          .sum();
    }

    private int computeReputationFromRing() {
        return attributs.sumRing() * 10;
    }

    public int rollInitiative() {
        RollAndKeep rollAndKeep = RollAndKeep.of(rank(), attributs.reflexe, 0)
                                             .add(generateBonusInitiative());
        RollAndKeepRequest request = RollAndKeepRequest.builder()
                                                       .dicePool(rollAndKeep)
                                                       .explodeOn(10)
                                                       .withoutEmphasis();
        return RollAndKeepDiceSystemFactory.D10.system.rollAndKeep(request).maxValue();
    }

    private RollAndKeep generateBonusInitiative() {
        return schools.stream()
                      .flatMap(School::availableRank)
                      .filter(InitiativeModifier.class::isInstance)
                      .map(InitiativeModifier.class::cast)
                      .map(initiativeModifier -> initiativeModifier.generateInitiativeBonus(this))
                      .reduce(RollAndKeep.zero(), RollAndKeep::add);
    }
}
