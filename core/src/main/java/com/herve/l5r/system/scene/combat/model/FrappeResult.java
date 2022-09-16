package com.herve.l5r.system.scene.combat.model;

import com.herve.l5r.system.roll.model.ComputedResult;

import java.util.Optional;

public record FrappeResult(ComputedResult hitResult, ComputedResult damageResult, boolean hasSucceed, int difficulty) {

    public static FrappeResult success(ComputedResult computedResult, ComputedResult damageResult, int difficulty) {
        return new FrappeResult(computedResult, damageResult, true, difficulty);
    }
    public static FrappeResult failed(ComputedResult computedResult, int difficulty) {
        return new FrappeResult(computedResult, ComputedResult.empty(),false, difficulty);
    }
}
