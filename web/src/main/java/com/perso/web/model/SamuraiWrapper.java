package com.perso.web.model;

import com.herve.l5r.system.model.Samurai;

public class SamuraiWrapper {
    public final String id;
    public final Samurai samurai;

    private SamuraiWrapper(String id, Samurai samurai) {
        this.id = id;
        this.samurai = samurai;
    }

    public static SamuraiWrapper of(String id, Samurai samurai) {
        return new SamuraiWrapper(id, samurai);
    }

}
