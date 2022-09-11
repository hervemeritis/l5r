package com.herve.l5r.system.model;

import com.herve.l5r.system.model.avantage.Avantage;
import com.herve.l5r.system.roll.CompetenceModifier;
import com.herve.l5r.system.roll.model.RollAndKeep;

import java.util.EnumMap;
import java.util.Set;
import java.util.function.Predicate;

public class Samurai implements CompetenceModifier {
    public final Family family;
    public final String name;
    public final Attribut attributs;
    public final double honneur;
    public final double gloire;
    public final double statut;
    public final EnumMap<CompetenceName, Competence> competences;

    public final Set<Avantage> avantages;

    Samurai(SamuraiBuilder builder) {
        family = builder.family;
        name = builder.name;
        attributs = builder.attributs;
        honneur = builder.honneur;
        gloire = builder.gloire;
        statut = builder.statut;
        competences = builder.competences.stream()
                                         .collect(() -> new EnumMap<>(CompetenceName.class), (map, cpt) -> map.put(cpt.name, cpt), EnumMap::putAll);
        avantages = Set.copyOf(builder.avantages);
    }

    public static SamuraiBuilder builder() {
        return new SamuraiBuilder();
    }

    @Override
    public RollAndKeep generateBonusToRollTo(Competence competence) {
        return avantages.stream()
                        .filter(avantage -> avantage instanceof CompetenceModifier)
                        .map(avantage -> (CompetenceModifier) avantage)
                        .map(competenceModifier -> competenceModifier.generateBonusToRollTo(competence))
                        .filter(Predicate.not(RollAndKeep::isZero))
                        .reduce(RollAndKeep.zero(), RollAndKeep::add);
    }
}
