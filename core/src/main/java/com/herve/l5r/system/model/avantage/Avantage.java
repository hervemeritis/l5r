package com.herve.l5r.system.model.avantage;

public enum Avantage {
    VIRTUOSE(new Virtuose()),
    FORCE_DE_LA_TERRE(new ForceDeLaTerre()),
    AME_D_ARTISTE(new AmeArtiste()),
    GRAND(new Grand());

    private final AvantageDefinition definition;


    Avantage(AvantageDefinition avantageDefinition) {
        this.definition = avantageDefinition;
    }

    public AvantageDefinition definition() {
        return definition;
    }
}
