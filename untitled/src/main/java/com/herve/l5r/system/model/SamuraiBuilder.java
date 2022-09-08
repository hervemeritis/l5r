package com.herve.l5r.system.model;

import java.util.List;
import java.util.Set;

public final class SamuraiBuilder {
    Family family;
    String name;
    Attribut attributs;
    double honneur;
    double gloire;
    double statut;
    Set<Competence> competences;

    SamuraiBuilder() {
    }

    public SamuraiBuilder family(Family family) {
        this.family = family;
        return this;
    }

    public SamuraiBuilder name(String name) {
        this.name = name;
        return this;
    }

    public SamuraiBuilder attributs(Attribut attributs) {
        this.attributs = attributs;
        return this;
    }

    public SamuraiBuilder honneur(double honneur) {
        this.honneur = honneur;
        return this;
    }

    public SamuraiBuilder gloire(double gloire) {
        this.gloire = gloire;
        return this;
    }

    public SamuraiBuilder statut(double statut) {
        this.statut = statut;
        return this;
    }

    public SamuraiBuilder competences(Set<Competence> competences) {
        this.competences = competences;
        return this;
    }

    public Samurai build() {
        return new Samurai(this);
    }
}
