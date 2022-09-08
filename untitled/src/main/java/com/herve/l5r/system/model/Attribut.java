package com.herve.l5r.system.model;

public class Attribut {
    public final int agilite;
    public final int intelligence;
    public final int reflexe;
    public final int intuition;
    public final int perception;
    public final int force;
    public final int volonte;
    public final int constitution;
    public final int vide;

    Attribut(AttributBuilder builder) {
        agilite = builder.agilite;
        intelligence = builder.intelligence;
        reflexe = builder.reflexe;
        intuition = builder.intuition;
        perception = builder.perception;
        force = builder.force;
        volonte = builder.volonte;
        constitution = builder.constitution;
        vide = builder.vide;
    }

    public static AttributBuilder builder() {
        return new AttributBuilder();
    }

    public int feu() {
        return Math.min(agilite, intelligence);
    }

    public int air() {
        return Math.min(reflexe, intuition);
    }

    public int eau() {
        return Math.min(force, perception);
    }

    public int terre() {
        return Math.min(volonte, constitution);
    }
}
