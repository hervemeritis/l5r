package com.herve.l5r.system.scene.iaijutsu;

import com.herve.l5r.system.model.Samurai;

public class DuelResult {
    private static final String TIE = "Egalité aucun Vainqueur";
    public final String duelistWinner;

    private DuelResult(String duelistWinner) {
        this.duelistWinner = duelistWinner;
    }

    public static DuelResult winner(Samurai samurai) {
        return new DuelResult(samurai.family.familyName + " " + samurai.name);
    }

    public static DuelResult tie() {
        return new DuelResult(TIE);
    }
}
