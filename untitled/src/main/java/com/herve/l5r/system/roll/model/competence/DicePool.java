package com.herve.l5r.system.roll.model.competence;

public interface DicePool {
    int keptDice();
    int modifier();

    int diceToRoll();
}
