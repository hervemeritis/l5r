package com.herve.l5r.system.model.avantage;

import com.herve.l5r.system.model.Samurai;

public class AmeArtiste implements AvantageDefinition{
    @Override
    public String description() {
        return "Le personnage est considéré comme ayant au moins un rang dans les compértence artistique";
    }

    @Override
    public String name() {
        return "Ame d'artiste";
    }

    @Override
    public int cost(Samurai samurai) {
        return 5;
    }
}
