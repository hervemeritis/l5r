package com.herve.l5r.system.roll.model;

import com.herve.l5r.system.roll.model.competence.DicePool;

class RollAndKeep implements DicePool {
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

        while(computedUnkeptDice + computedKeptDice > 10 && computedKeptDice != 0) {
            computedUnkeptDice--;
            if (computedUnkeptDice + computedKeptDice  > 10) {
                computedUnkeptDice--;
                computedKeptDice++;
            }
        }

        int computedModifier = modifier;
        if (computedUnkeptDice > 10) {
            computedModifier += (computedKeptDice - 10) * 10;
            computedUnkeptDice = 10;
        }

        return RollAndKeep.of(computedUnkeptDice, computedKeptDice, computedModifier);
    }

    public RollAndKeep increaseUnkept(int value) {
        return RollAndKeep.of(unkept + value, kept, modifier);
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
}
