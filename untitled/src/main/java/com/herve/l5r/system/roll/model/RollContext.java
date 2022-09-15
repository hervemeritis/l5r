package com.herve.l5r.system.roll.model;

import com.herve.l5r.system.model.Samurai;

public class RollContext {
    public final Samurai samurai;

    protected RollContext(Samurai samurai) {
        this.samurai = samurai;
    }

    public static RollContext of(Samurai samurai) {
        return new RollContext(samurai);
    }
}
