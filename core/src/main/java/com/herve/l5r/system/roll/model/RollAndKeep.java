package com.herve.l5r.system.roll.model;

public class RollAndKeep implements DicePool {
    public final int unkept;
    public final int kept;
    public final int modifier;

    private RollAndKeep(int unkept, int kept, int modifier) {
        this.unkept = unkept;
        this.kept = kept;
        this.modifier = modifier;
    }

    public static RollAndKeep of(int unkept, int kept, int modifier) {
        return new RollAndKeep(unkept, kept, modifier);
    }


    public DicePool compute() {
        int computedUnkeptDice = unkept;
        int computedKeptDice = kept;

        if (diceToRoll() < keptDice()) {
            computedKeptDice = diceToRoll();
            computedUnkeptDice = 0;
        }

        if (diceToRoll() > 10 && unkept > 0) {
            int flatKeptDice = Math.min(kept, 10);
            int nbDiceMoreThan10 = unkept + flatKeptDice - 10;
            computedUnkeptDice = kept < 10 ? unkept - (nbDiceMoreThan10 * 3) / 2 : 0;
            computedKeptDice = kept + nbDiceMoreThan10 / 2;
        }

        int computedModifier = modifier;
        if (computedKeptDice > 10) {
            computedModifier += (computedKeptDice - 10) * 10;
            computedKeptDice = 10;
        }


        return RollAndKeep.of(computedUnkeptDice, computedKeptDice, computedModifier);
    }

    public RollAndKeep increaseUnkept(int value) {
        return RollAndKeep.of(unkept + value, kept, modifier);
    }

    public static RollAndKeep zero() {
        return DefaultRoll.ZERO.roll;
    }

    public static RollAndKeep vide() {
        return DefaultRoll.VIDE.roll;
    }

    public boolean isZero() {
        return unkept == 0 && kept == 0 && modifier == 0;
    }

    public RollAndKeep add(RollAndKeep rollAndKeep) {
        return RollAndKeep.of(unkept + rollAndKeep.unkept, kept + rollAndKeep.kept, modifier + rollAndKeep.modifier);
    }

    public String toString() {
        return diceToRoll() + "K" + keptDice() + "+" + modifier();
    }

    @Override
    public int keptDice() {
        return kept;
    }

    @Override
    public int modifier() {
        return modifier;
    }

    public int diceToRoll() {
        return unkept + kept;
    }

    private enum DefaultRoll {
        ZERO(RollAndKeep.of(0, 0, 0)),
        VIDE(RollAndKeep.of(0, 1, 0));

        private final RollAndKeep roll;

        DefaultRoll(RollAndKeep roll) {
            this.roll = roll;
        }
    }
}
