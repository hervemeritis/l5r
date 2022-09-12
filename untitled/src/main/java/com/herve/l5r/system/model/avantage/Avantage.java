package com.herve.l5r.system.model.avantage;

public enum Avantage {
    VIRTUOSE(new Virtuose());

    private final AvantageDefinition definition;


    Avantage(AvantageDefinition avantageDefinition) {
        this.definition = avantageDefinition;
    }

    public AvantageDefinition definition() {
        return definition;
    }
}
