package com.herve.l5r.system.roll;

import com.herve.l5r.system.roll.model.ComputedResult;
import com.herve.l5r.system.roll.model.Counter;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;
import com.herve.l5r.system.roll.model.DicePool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class RollAndKeepDiceSystem {

    private final Supplier<Integer> diceRoll;


    public RollAndKeepDiceSystem(int nbDiceFace) {
        this.diceRoll = () -> new Random().nextInt(nbDiceFace) + 1;
    }

    RollAndKeepDiceSystem(Supplier<Integer> diceRoll) {
        this.diceRoll = diceRoll;
    }

    public RollResult rollAndKeep(RollAndKeepRequest rollAndKeepRequest) {
        DicePool dicePool = rollAndKeepRequest.dicePool();
        return IntStream.range(0, 10)
                        .takeWhile(i -> dicePool.diceToRoll() > i)
                        .mapToObj(__ -> DiceResult.explodingDice(rollAndKeepRequest.explodeOn, rollAndKeepRequest.emphasis, diceRoll))
                        .collect(() -> new RollResult(dicePool), RollResult::addDiceResult, RollResult::aggregate);
    }

    public static class RollResult {

        private final DicePool dicePoolUsed;
        private final ArrayList<DiceResult> diceResults = new ArrayList<>();


        private RollResult(DicePool dicePoolUsed) {
            this.dicePoolUsed = dicePoolUsed;
        }

        private void addDiceResult(DiceResult diceResult) {
            this.diceResults.add(diceResult);
        }

        private void aggregate(RollResult rollResult) {
            this.diceResults.addAll(rollResult.diceResults);
        }

        public ComputedResult maxValue() {
            if (diceResults.isEmpty()) {
                return new ComputedResult(new int[]{}, dicePoolUsed.toString(), dicePoolUsed.modifier());
            }
            final Counter counter = new Counter();
            int maxValue = diceResults.stream()
                                      .map(diceResult -> diceResult.value)
                                      .sorted(Comparator.reverseOrder())
                                      .mapToInt(Integer::intValue)
                                      .takeWhile(__ -> counter.getAndIncrement() < dicePoolUsed.keptDice())
                                      .sum() + dicePoolUsed.modifier();
            return new ComputedResult(diceResults.stream().mapToInt(DiceResult::value).toArray(), dicePoolUsed.toString(), maxValue);
        }

        public String toString() {
            return dicePoolUsed.diceToRoll() + "k" + dicePoolUsed.keptDice() + " + " + dicePoolUsed.modifier();
        }

    }

    private record DiceResult(int value) {
        private static DiceResult of(int value) {
            return new DiceResult(value);
        }

        private static DiceResult explodingDice(int explosionValue, boolean emphasis, Supplier<Integer> diceRoll) {
            int result = 0;
            boolean needToExplode;
            do {
                int rollResult = diceRoll.get();
                needToExplode = rollResult >= explosionValue;
                result += rollResult;
            } while (needToExplode);

            if (result == 1 && emphasis) {
                return explodingDice(explosionValue, false, diceRoll);
            }
            return DiceResult.of(result);
        }
    }

}
