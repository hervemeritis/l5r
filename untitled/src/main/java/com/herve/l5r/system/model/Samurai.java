package com.herve.l5r.system.model;

import java.util.List;
import java.util.Set;

public class Samurai {
    public final Family family;
    public final String name;
    public final Attribut attributs;
    public final double honneur;
    public final double gloire;
    public final double statut;
    public final Set<Competence> competences;

    Samurai(SamuraiBuilder builder) {
        family = builder.family;
        name = builder.name;
        attributs = builder.attributs;
        honneur = builder.honneur;
        gloire = builder.gloire;
        statut = builder.statut;
        competences = builder.competences;
    }

    public static SamuraiBuilder builder() {
        return new SamuraiBuilder();
    }

}
