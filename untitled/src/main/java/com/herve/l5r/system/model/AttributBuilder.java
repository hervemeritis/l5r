package com.herve.l5r.system.model;

public final class AttributBuilder {
    int agilite;
    int intelligence;
    int reflexe;
    int intuition;
    int perception;
    int force;
    int volonte;
    int constitution;
    int vide;

    AttributBuilder() {
    }

    public AttributBuilder agilite(int agilite) {
        this.agilite = agilite;
        return this;
    }

    public AttributBuilder intelligence(int intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public AttributBuilder reflexe(int reflexe) {
        this.reflexe = reflexe;
        return this;
    }

    public AttributBuilder intuition(int intuition) {
        this.intuition = intuition;
        return this;
    }

    public AttributBuilder perception(int perception) {
        this.perception = perception;
        return this;
    }

    public AttributBuilder force(int force) {
        this.force = force;
        return this;
    }

    public AttributBuilder volonte(int volonte) {
        this.volonte = volonte;
        return this;
    }

    public AttributBuilder constitution(int constitution) {
        this.constitution = constitution;
        return this;
    }

    public AttributBuilder vide(int vide) {
        this.vide = vide;
        return this;
    }

    public Attribut build() {
        return new Attribut(this);
    }
}
