package com.herve.l5r.system.model;

public class RollAndKeepRequest {
    public static final int NON_EXPLODING_VALUE = 11;
    private int diceToKeep;
    private int diceToRoll;
    public final int modifier;
    public final int explodeOn;

    public final boolean emphasis;

    private RollAndKeepRequest(int diceToKeep, int diceToRoll, int modifier, int explodeOn, boolean emphasis) {
        this.diceToKeep = diceToKeep;
        this.diceToRoll = diceToRoll;
        this.modifier = modifier;
        this.explodeOn = explodeOn;
        this.emphasis = emphasis;
    }

    public int diceToKeep() {
        return diceToKeep;
    }

    public int diceToRoll() {
        return diceToRoll;
    }

    public static UnkeptDice withoutEmphasis() {
        return builder(false);
    }

    public static UnkeptDice withEmphasis(boolean emphasis) {
        return builder(emphasis);
    }

    private static UnkeptDice builder(boolean emphasis) {
        return unkeepRoll -> keepRoll -> diceExplosionvalue -> modifier ->
                new RollAndKeepRequest(keepRoll, unkeepRoll + keepRoll, modifier, diceExplosionvalue, emphasis)
                        .validate()
                        .compute();
    }

    private RollAndKeepRequest validate() {
        if (diceToKeep > 10) {
            throw new RuntimeException("On ne peut pas garder plus de 10 dés");
        }
        return this;
    }

    private RollAndKeepRequest compute() {
        return new RollAndKeepRequest(computeDiceToKeep(), diceToRoll, computeModifier(), explodeOn, emphasis);
    }

    private int computeDiceToKeep() {
        while (diceToRoll > 10 && diceToKeep < 10) {
            decrementRollToKeep();
        }
        return Math.min(diceToKeep, diceToRoll);
    }

    private void decrementRollToKeep() {
        diceToRoll--;
        if (diceToRoll > 10) {
            diceToRoll--;
            diceToKeep++;
        }
    }


    private int computeModifier() {
        int pairOfDiceAfterTen = diceToRoll > 10 ? (diceToRoll - 10) / 2 : 0;
        return modifier + pairOfDiceAfterTen * 10;
    }

    public interface UnkeptDice {
        DiceToKeep unkeptDice(int nbDice);
    }

    public interface DiceToKeep {
        Explode diceToKeep(int nbDice);

        default Modifier defaultExplodingDiceToKeep(int nbDice) {
            return diceToKeep(nbDice).explodeOn(10);
        }
    }

    public interface Explode {
        Modifier explodeOn(int minValue);

    }

    public interface Modifier {
        RollAndKeepRequest modifier(int modifier);

        default RollAndKeepRequest withoutModifier() {
            return modifier(0);
        }
    }
}
