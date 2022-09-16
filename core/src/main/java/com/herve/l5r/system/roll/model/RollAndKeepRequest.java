package com.herve.l5r.system.roll.model;

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
        return unkeepRoll -> keepRoll -> modifier -> diceExplosionvalue -> emphasis ->
                new RollAndKeepRequest(RollAndKeep.of(unkeepRoll, keepRoll, modifier), diceExplosionvalue, emphasis);
    }

    public DicePool dicePool() {
        return rawDicePool.compute();
    }

    public interface UnkeptDice {
        DiceToKeep unkeptDice(int nbDice);

        default Explode dicePool(RollAndKeep rollAndKeep) {
            return modifier -> emphasis -> new RollAndKeepRequest(rollAndKeep, modifier, emphasis);
        }
    }

    public interface DiceToKeep {
        Modifier diceToKeep(int nbDice);

    }
    public interface Modifier {

        Explode modifier(int modifier);
        default Explode withoutModifier() {
            return modifier(0);
        }


    }
    public interface Explode {

        Emphasis explodeOn(int minValue);

        default RollAndKeepRequest build() {
            return explodeOn(10).emphasis(false);
        }

        default Emphasis defaultExplodingDice() {
            return explodeOn(10);
        }

    }

    public interface Emphasis {
        RollAndKeepRequest emphasis(boolean emphasis);

        default RollAndKeepRequest withoutEmphasis() {
            return emphasis(false);
        }
    }
}
