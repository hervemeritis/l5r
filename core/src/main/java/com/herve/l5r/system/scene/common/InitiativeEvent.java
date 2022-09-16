package com.herve.l5r.system.scene.common;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.roll.model.ComputedResult;
import com.herve.l5r.system.scene.logger.Event;

public class InitiativeEvent implements Event {
    public final Samurai samurai;
    public final ComputedResult initiative;

    public InitiativeEvent(Samurai samurai, ComputedResult initiative) {
        this.samurai = samurai;
        this.initiative = initiative;
    }


    @Override
    public String message() {
        return samurai.fullName() + " has rolled iniative \n" + " -> " + initiative.toString();
    }
}
