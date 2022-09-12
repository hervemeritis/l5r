package com.herve.l5r.system.model;

import com.herve.l5r.system.model.avantage.Avantage;
import com.herve.l5r.system.model.school.School;
import com.herve.l5r.system.roll.model.competence.CompetenceModifier;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.competence.CompetenceRollContext;

import java.util.EnumMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Samurai implements CompetenceModifier {
    public final Family family;
    public final String name;
    public final Attribut attributs;
    public final double honneur;
    public final double gloire;
    public final double statut;
    public final EnumMap<CompetenceName, Competence> competences;

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
                                         .collect(() -> new EnumMap<>(CompetenceName.class), (map, cpt) -> map.put(cpt.name, cpt), EnumMap::putAll);
        this.avantages = Set.copyOf(builder.avantages);
        this.schools = builder.schools;
    }

    public static SamuraiBuilder builder() {
        return new SamuraiBuilder();
    }

    @Override
    public RollAndKeep generateBonusToRollWith(CompetenceRollContext context) {
        return Stream.concat(avantages.stream().map(Avantage::definition), schools.stream().flatMap(School::availableRank))
                     .filter(o -> o instanceof CompetenceModifier)
                     .map(o -> (CompetenceModifier) o)
                     .map(cptModifier -> cptModifier.generateBonusToRollWith(context))
                     .reduce(RollAndKeep.zero(), RollAndKeep::add);
    }

    public int rank() {
        int reputation = computeReputationFromCompetence() + computeReputationFromRing();
        return reputation > 149 ? (reputation - 150) / 25 + 2 : 1;
    }

    private int computeReputationFromCompetence() {
        return competences.values()
                          .stream()
                          .mapToInt(cpt -> cpt.value)
                          .sum();
    }

    private int computeReputationFromRing() {
        return attributs.sumRing() * 10;
    }
}
