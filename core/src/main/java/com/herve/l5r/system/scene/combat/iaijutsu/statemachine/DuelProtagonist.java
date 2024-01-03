package com.herve.l5r.system.scene.combat.iaijutsu.statemachine;

import com.herve.l5r.system.model.competence.CompetenceTypeRoll;
import com.herve.l5r.system.roll.model.ComputedResult;
import com.herve.l5r.system.roll.model.DicePool;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.competence.CompetenceRollContext;
import com.herve.l5r.system.scene.Protagonist;
import com.herve.l5r.system.scene.combat.model.FrappeResult;

public class DuelProtagonist {
    private final Protagonist protagonist;
    private int nbDuelStatKnown;

    private ComputedResult assesmentResult;

    public DuelProtagonist(Protagonist protagonist) {
        this.protagonist = protagonist;
    }


    public void iaijutsuAssesment() {
        assesmentResult = protagonist.evaluate(CompetenceTypeRoll.IAJUTSU_EVALUATION);
        int assesmentDifficulty = protagonist.opponent().samurai.rank() * 5 + 10;
        if (assesmentResult.result() > assesmentDifficulty) {
            nbDuelStatKnown = 1;
        }
    }


    public FrappeResult iaijutsuFrappe(int ndGratuit) {
        ComputedResult frappeResult = protagonist.evaluate(CompetenceTypeRoll.IAJUTSU_FRAPPE);
        int nd = 0;
        if (nbDuelStatKnown > 0) {
            DicePool dicePool = CompetenceRollContext.of(CompetenceTypeRoll.IAJUTSU_FRAPPE, protagonist.samurai).generateCompetenceRoll().compute();
            int diff = (dicePool.keptDice() * 6 + dicePool.modifier() - protagonist.opponent().TNToBeHit()) / 5;
            nd = Math.min(Math.max(0, diff), protagonist.samurai.attributs.vide);
        }
        int difficulty = protagonist.opponent().TNToBeHit() + 5 * nd;
        if (frappeResult.result() < difficulty) {
            return FrappeResult.failed(frappeResult, difficulty);
        }
        RollAndKeep bonus = protagonist.weaponDrawn().canUseVide() ? RollAndKeep.vide() : RollAndKeep.zero();
        ComputedResult damage = protagonist.applyDamage(nd + ndGratuit, bonus);
        return FrappeResult.success(frappeResult, damage, difficulty);
    }
}
