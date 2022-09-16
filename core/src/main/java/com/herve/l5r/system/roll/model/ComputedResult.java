package com.herve.l5r.system.roll.model;

import java.util.Arrays;

public record ComputedResult(int[] diceResults, String dicePool, int result) {
    public static ComputedResult empty() {
        return new ComputedResult(new int[]{}, RollAndKeep.zero().toString(), 0);
    }

    public String toString() {
        return "Roll " + dicePool + " with result " + result + " " + Arrays.toString(diceResults);
    }
}
