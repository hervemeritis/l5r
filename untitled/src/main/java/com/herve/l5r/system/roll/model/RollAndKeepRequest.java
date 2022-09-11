package com.herve.l5r.system.roll.model;

import com.herve.l5r.system.roll.model.competence.DicePool;

public class RollAndKeepRequest {
    public static final int NON_EXPLODING_VALUE = 11;

    private final RollAndKeep rawDicePool;

    public final int explodeOn;

    public final boolean emphasis;

    private RollAndKeepRequest(RollAndKeep rollAndKeep, int explodeOn, boolean emphasis) {
        this.rawDicePool = rollAndKeep;
        this.explodeOn = explodeOn;
        this.emphasis = emphasis;
    }

    public static UnkeptDice builder() {
        return unkeepRoll -> keepRoll -> diceExplosionvalue -> modifier -> emphasis ->
                new RollAndKeepRequest(RollAndKeep.of(unkeepRoll, keepRoll, modifier), diceExplosionvalue, emphasis);
    }

    public DicePool dicePool() {
        return rawDicePool.compute();
    }

    public interface UnkeptDice {
        DiceToKeep unkeptDice(int nbDice);

        default Modifier dicePool(RollAndKeep rollAndKeep) {
            return modifier -> emphasis -> new RollAndKeepRequest(rollAndKeep, modifier, emphasis);
        }
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
        Emphasis modifier(int modifier);

        default Emphasis withoutModifier() {
            return modifier(0);
        }

        default RollAndKeepRequest build() {
            return modifier(0).emphasis(false);
        }
    }

    public interface Emphasis {
        RollAndKeepRequest emphasis(boolean emphasis);

        default RollAndKeepRequest withoutEmphasis() {
            return emphasis(false);
        }
    }
}
