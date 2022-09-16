package com.herve.l5r.system.scene;

import com.herve.l5r.system.model.Attribut;
import com.herve.l5r.system.model.Family;
import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.avantage.Avantage;
import com.herve.l5r.system.model.competence.CompetenceName;
import com.herve.l5r.system.model.competence.CompetenceTypeRoll;
import com.herve.l5r.system.model.school.FamilySchool;
import com.herve.l5r.system.model.school.School;
import com.herve.l5r.system.model.weapon.WeaponType;
import com.herve.l5r.system.roll.RollAndKeepDiceSystemFactory;
import com.herve.l5r.system.roll.model.ComputedResult;
import com.herve.l5r.system.roll.model.DicePool;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;
import com.herve.l5r.system.roll.model.competence.CompetenceRollContext;
import com.herve.l5r.system.scene.combat.model.FrappeResult;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Protagonist {
    public final Samurai samurai;
    private ComputedResult initiative;
    private ComputedResult lastResult;
    private Protagonist opponent;
    private final Queue<RollAndKeep> temporaryBonus = new LinkedList<>();

    private int wounds = 0;

    private WeaponType weaponTypeDrawn = WeaponType.FIST;

    private int nbDuelStatKnown;

    private Protagonist(Samurai samurai) {
        this.samurai = samurai;
    }

    public static Protagonist of(Samurai samurai) {
        return new Protagonist(samurai);
    }

    public Protagonist withOpponent(Protagonist protagonist) {
        this.opponent = protagonist;
        return this;
    }

    public Protagonist draw(WeaponType weaponType) {
        this.weaponTypeDrawn = weaponType;
        return this;

    }

    public ComputedResult initiative() {
        return initiative;
    }

    public ComputedResult lastResult() {
        return lastResult;
    }

    public Protagonist opponent() {
        return opponent;
    }

    public ComputedResult computeInitiative() {
        initiative = samurai.rollInitiative();
        return initiative;
    }

    public ComputedResult evaluate(CompetenceTypeRoll typeRoll) {
        return evaluate(typeRoll, RollAndKeep.zero());
    }

    private ComputedResult evaluate(CompetenceTypeRoll typeRoll, RollAndKeep bonus) {
        while (!temporaryBonus.isEmpty()) {
            bonus = bonus.add(temporaryBonus.remove());
        }
        RollAndKeepRequest request = CompetenceRollContext.of(typeRoll, samurai).withTemporaryBonus(bonus).generateCompetenceRollRequest();
        ComputedResult computedResult = RollAndKeepDiceSystemFactory.D10.system.rollAndKeep(request).maxValue();
        lastResult = computedResult;
        return computedResult;
    }

    public int resultDifferenceWithOpponent() {
        return lastResult.result() - opponent.lastResult.result();
    }

    public void addTemporaryBonus(RollAndKeep rollAndKeep) {
        temporaryBonus.offer(rollAndKeep);
    }

    public int TNToBeHit() {
        return samurai.baseTNToBeHit();
    }

    public ComputedResult iaijutsuAssesment() {
        ComputedResult assesmentResult = evaluate(CompetenceTypeRoll.IAJUTSU_EVALUATION);
        int assesmentDifficulty = opponent.samurai.rank() * 5 + 10;
        if (assesmentResult.result() > assesmentDifficulty) {
            nbDuelStatKnown = 1;
        }
        return assesmentResult;
    }


    public FrappeResult iaijutsuFrappe(int ndGratuit) {
        ComputedResult frappeResult = evaluate(CompetenceTypeRoll.IAJUTSU_FRAPPE);
        int nd = 0;
        if (nbDuelStatKnown > 0) {
            DicePool dicePool = CompetenceRollContext.of(CompetenceTypeRoll.IAJUTSU_FRAPPE, samurai).generateCompetenceRoll().compute();
            int diff = (dicePool.keptDice() * 6 + dicePool.modifier() - opponent.TNToBeHit()) / 5;
            nd = Math.min(Math.max(0, diff), samurai.attributs.vide);
        }
        int difficulty = opponent.TNToBeHit() + 5 * nd;
        if (frappeResult.result() < difficulty) {
            return FrappeResult.failed(frappeResult, difficulty);
        }
        RollAndKeep bonus = weaponTypeDrawn.canUseVide() ? RollAndKeep.vide() : RollAndKeep.zero();
        ComputedResult damage = applyDamage(nd + ndGratuit, bonus);
        return FrappeResult.success(frappeResult, damage, difficulty);
    }

    private ComputedResult applyDamage(int nd, RollAndKeep bonus) {
        RollAndKeepRequest rollAndKeepRequest = weaponTypeDrawn.generateDamageRoll(samurai, nd, bonus);
        ComputedResult computedResult = RollAndKeepDiceSystemFactory.D10.system.rollAndKeep(rollAndKeepRequest).maxValue();
        opponent().wounds += computedResult.result();
        return computedResult;
    }

    public String name() {
        return samurai.fullName();
    }
}