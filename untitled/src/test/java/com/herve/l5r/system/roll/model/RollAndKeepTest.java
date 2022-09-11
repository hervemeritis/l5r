package com.herve.l5r.system.roll.model;


import com.herve.l5r.system.roll.model.competence.DicePool;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RollAndKeepTest {

    @Test
    public void should_keep_value_when_compute() {
        //Given
        RollAndKeep rollAndKeep = RollAndKeep.of(6, 4, 0);

        //When
        DicePool dicePool = rollAndKeep.compute();

        //Then
        Assertions.assertThat(dicePool.diceToRoll()).isEqualTo(10);
        Assertions.assertThat(dicePool.keptDice()).isEqualTo(4);
        Assertions.assertThat(dicePool.modifier()).isEqualTo(0);
    }

    @Test
    public void should_decrease_unkeptdice_when_compute() {
        //Given
        RollAndKeep rollAndKeep = RollAndKeep.of(-1, 4, 1);

        //When
        DicePool dicePool = rollAndKeep.compute();

        //Then
        Assertions.assertThat(dicePool.diceToRoll()).isEqualTo(3);
        Assertions.assertThat(dicePool.keptDice()).isEqualTo(3);
        Assertions.assertThat(dicePool.modifier()).isEqualTo(1);
    }

    @Test
    public void should_not_increase_unkept_dice() {
        //Given
        RollAndKeep rollAndKeep = RollAndKeep.of(7, 4, 1);

        //When
        DicePool dicePool = rollAndKeep.compute();

        //Then
        Assertions.assertThat(dicePool.diceToRoll()).isEqualTo(10);
        Assertions.assertThat(dicePool.keptDice()).isEqualTo(4);
        Assertions.assertThat(dicePool.modifier()).isEqualTo(1);
    }

    @Test
    void should_increase_once_unkept_dice() {
        //Given
        RollAndKeep rollAndKeep = RollAndKeep.of(9, 4, 1);

        //When
        DicePool dicePool = rollAndKeep.compute();

        //Then
        Assertions.assertThat(dicePool.diceToRoll()).isEqualTo(10);
        Assertions.assertThat(dicePool.keptDice()).isEqualTo(5);
        Assertions.assertThat(dicePool.modifier()).isEqualTo(1);
    }

    @Test
    void should_increase_multiple_unkept_dice() {
        //Given
        RollAndKeep rollAndKeep = RollAndKeep.of(9, 5, 1);

        //When
        DicePool dicePool = rollAndKeep.compute();

        //Then
        Assertions.assertThat(dicePool.diceToRoll()).isEqualTo(10);
        Assertions.assertThat(dicePool.keptDice()).isEqualTo(7);
        Assertions.assertThat(dicePool.modifier()).isEqualTo(1);
    }

    @Test
    void should_decrease_unkept_dice() {
        //Given
        RollAndKeep rollAndKeep = RollAndKeep.of(-2, 11, 1);

        //When
        DicePool dicePool = rollAndKeep.compute();

        //Then
        Assertions.assertThat(dicePool.diceToRoll()).isEqualTo(9);
        Assertions.assertThat(dicePool.keptDice()).isEqualTo(9);
        Assertions.assertThat(dicePool.modifier()).isEqualTo(1);
    }

    @Test
    void should_not_increase_bonus() {
        //Given
        RollAndKeep rollAndKeep = RollAndKeep.of(1, 10, 1);

        //When
        DicePool dicePool = rollAndKeep.compute();

        //Then
        Assertions.assertThat(dicePool.diceToRoll()).isEqualTo(10);
        Assertions.assertThat(dicePool.keptDice()).isEqualTo(10);
        Assertions.assertThat(dicePool.modifier()).isEqualTo(1);
    }

    @Test
    void should_increase_bonus_once() {
        //Given
        RollAndKeep rollAndKeep = RollAndKeep.of(3, 10, 1);

        //When
        DicePool dicePool = rollAndKeep.compute();

        //Then
        Assertions.assertThat(dicePool.diceToRoll()).isEqualTo(10);
        Assertions.assertThat(dicePool.keptDice()).isEqualTo(10);
        Assertions.assertThat(dicePool.modifier()).isEqualTo(11);
    }

    @Test
    void should_increase_bonus_twice() {
        //Given
        RollAndKeep rollAndKeep = RollAndKeep.of(2, 11, 1);

        //When
        DicePool dicePool = rollAndKeep.compute();

        //Then
        Assertions.assertThat(dicePool.diceToRoll()).isEqualTo(10);
        Assertions.assertThat(dicePool.keptDice()).isEqualTo(10);
        Assertions.assertThat(dicePool.modifier()).isEqualTo(21);
    }

    @Test
    void should_increase_bonus_three_time() {
        //Given
        RollAndKeep rollAndKeep = RollAndKeep.of(5, 11, 1);

        //When
        DicePool dicePool = rollAndKeep.compute();

        //Then
        Assertions.assertThat(dicePool.diceToRoll()).isEqualTo(10);
        Assertions.assertThat(dicePool.keptDice()).isEqualTo(10);
        Assertions.assertThat(dicePool.modifier()).isEqualTo(31);
    }
}