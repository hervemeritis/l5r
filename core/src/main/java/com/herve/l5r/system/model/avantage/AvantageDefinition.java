package com.herve.l5r.system.model.avantage;

import com.herve.l5r.system.model.Samurai;

public interface AvantageDefinition {

    String description();
    String name();
    int cost(Samurai samurai);


}
