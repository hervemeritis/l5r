package com.herve.l5r.system.scene;

import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.weapon.WeaponType;
import com.herve.l5r.system.model.competence.CompetenceTypeRoll;
import com.herve.l5r.system.roll.RollAndKeepDiceSystemFactory;
import com.herve.l5r.system.roll.model.RollAndKeep;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;
import com.herve.l5r.system.roll.model.competence.CompetenceRollContext;

import java.util.LinkedList;
import java.util.Queue;

public class Protagonist {
    public final Samurai samurai;
    private int initiative;
    private int lastResult;
    private Protagonist opponent;
    private final Queue<RollAndKeep> temporaryBonus = new LinkedList<>();

    private int wounds = 0;

    private WeaponType weaponTypeDrawn = WeaponType.FIST;

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

    public int initiative() {
        return initiative;
    }

    public int lastResult() {
        return lastResult;
    }

    public Protagonist opponent() {
        return opponent;
    }

    public int computeInitiative() {
        initiative = samurai.rollInitiative();
        return initiative;
    }

    public int evaluate(CompetenceTypeRoll typeRoll) {
        RollAndKeep bonus = RollAndKeep.zero();
        while(!temporaryBonus.isEmpty()) {
            bonus = bonus.add(temporaryBonus.remove());
        }
        RollAndKeepRequest request = CompetenceRollContext.of(typeRoll, samurai).withTemporaryBonus(bonus).generateCompetenceRollRequest();
        lastResult = RollAndKeepDiceSystemFactory.D10.system.rollAndKeep(request).maxValue();
        return lastResult;
    }

    public int resultDifferenceWithOpponent() {
        return lastResult - opponent.lastResult;
    }

    public void addTemporaryBonus(RollAndKeep rollAndKeep) {
        temporaryBonus.offer(rollAndKeep);
    }

    public int TNToBeHit() {
        return samurai.baseTNToBeHit();
    }

    public boolean iaijutsuFrappe(int nd, int ndGratuit) {
        if(evaluate(CompetenceTypeRoll.IAJUTSU_FRAPPE) < opponent.TNToBeHit() + 5 * nd) {
            return false;
        }
        applyDamage(nd + ndGratuit);
        return true;
    }

    private void applyDamage(int nd) {
        int damage = RollAndKeepDiceSystemFactory.D10.system.rollAndKeep(weaponTypeDrawn.generateDamageRoll(samurai, nd)).maxValue();
        opponent().wounds += damage;
    }
}