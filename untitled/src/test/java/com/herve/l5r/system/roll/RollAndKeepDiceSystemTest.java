package com.herve.l5r.system.roll;

import com.herve.l5r.system.roll.model.RollAndKeepRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


class RollAndKeepDiceSystemTest {

    @Test
    public void should_launch_three_dice() {
        //Given
        List<Integer> integers = List.of(10, 3, 4, 6, 7, 8, 3);
        Queue<Integer> fixedDiceResult = new LinkedList<>(integers);
        RollAndKeepDiceSystem rollAndKeepDiceSystem = new RollAndKeepDiceSystem(fixedDiceResult::remove);
        RollAndKeepRequest request = RollAndKeepRequest.builder()
                                                       .unkeptDice(3)
                                                       .diceToKeep(3)
                                                       .modifier(0)
                                                       .build();

        //When
        RollAndKeepDiceSystem.RollResult rollResult = rollAndKeepDiceSystem.rollAndKeep(request);

        //Then
        Assertions.assertThat(rollResult.maxValue()).isEqualTo(28);
    }

}