package com.herve.l5r.system.scene.combat.iaijutsu;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.scene.logger.EventLogger;

public class DuelResult {
    private static final String TIE = "Egalité aucun Vainqueur";
    public final String duelistWinner;

    private EventLogger eventLogger;

    private DuelResult(String duelistWinner) {
        this.duelistWinner = duelistWinner;
    }

    public static DuelResult winner(Samurai samurai) {
        return new DuelResult(samurai.fullName());
    }

    public static DuelResult tie() {
        return new DuelResult(TIE);
    }

    public DuelResult with(EventLogger eventLogger) {
        this.eventLogger = eventLogger;
        return this;
    }

    public EventLogger eventLogger() {
        return eventLogger;
    }
}
