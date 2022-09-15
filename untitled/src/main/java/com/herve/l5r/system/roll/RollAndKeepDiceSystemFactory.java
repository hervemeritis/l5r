package com.herve.l5r.system.roll;

public enum RollAndKeepDiceSystemFactory {
    D10(new RollAndKeepDiceSystem(10));

    public final RollAndKeepDiceSystem system;

    RollAndKeepDiceSystemFactory(RollAndKeepDiceSystem diceSystem) {
        this.system = diceSystem;
    }
}
