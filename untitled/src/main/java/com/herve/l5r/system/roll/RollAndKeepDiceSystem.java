package com.herve.l5r.system.roll;

import com.herve.l5r.system.model.Counter;
import com.herve.l5r.system.model.RollAndKeepRequest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;

public class RollAndKeepDiceSystem {

    public RollResult rollAndKeep(RollAndKeepRequest rollAndKeepRequest) {
        return IntStream.range(0, 10)
                        .takeWhile(i -> rollAndKeepRequest.diceToRoll() > i)
                        .mapToObj(__ -> DiceResult.explodingDice(rollAndKeepRequest.explodeOn, rollAndKeepRequest.emphasis))
                        .collect(() -> new RollResult(rollAndKeepRequest.diceToKeep(), rollAndKeepRequest.modifier), RollResult::addDiceResult, RollResult::aggregate);
    }

    public static class RollResult {

        private final int modifier;
        private final ArrayList<DiceResult> diceResults = new ArrayList<>();

        private final int nbResultToKeep;


        private RollResult(int nbResultToKeep, int modifier) {
            this.modifier = modifier;
            this.nbResultToKeep = nbResultToKeep;
        }

        private void addDiceResult(DiceResult diceResult) {
            this.diceResults.add(diceResult);
        }

        private void aggregate(RollResult rollResult) {
            this.diceResults.addAll(rollResult.diceResults);
        }

        public int maxValue() {
            if (diceResults.isEmpty()) {
                return modifier;
            }
            final Counter counter = new Counter();
            return diceResults.stream()
                              .map(diceResult -> diceResult.value)
                              .peek(dice -> System.out.println(dice))
                              .sorted(Comparator.reverseOrder())
                              .mapToInt(Integer::intValue)
                              .takeWhile(__ -> counter.getAndIncrement() < nbResultToKeep)
                              .sum() + modifier;

        }

    }

    private record DiceResult(int value) {
        private static DiceResult of(int value) {
            return new DiceResult(value);
        }

        private static DiceResult explodingDice(int explosionValue, boolean emphasis) {
            Random dice = new Random();
            int result = 0;
            boolean needToExplode;
            do {
                int rollResult = dice.nextInt(10) + 1;
                needToExplode = rollResult >= explosionValue;
                result += rollResult;
            } while (needToExplode);

            if (result == 1 && emphasis) {
                return explodingDice(explosionValue, false);
            }
            return DiceResult.of(result);
        }
    }

}
