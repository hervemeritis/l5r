package com.herve.l5r.system;

import com.herve.l5r.system.sample.SamuraiFactory;
import com.herve.l5r.system.scene.combat.iaijutsu.DuelResult;
import com.herve.l5r.system.scene.combat.iaijutsu.IaijutsuDuel;

public class DuelTest {
    public static void main(String[] args) {
        DuelResult result = IaijutsuDuel.between(SamuraiFactory.kakita(), SamuraiFactory.kakita())
                                        .rollInitiative()
                                        .evaluation()
                                        .concentration()
                                        .frappe();
        result.eventLogger().print();
        System.out.println("Winner is " + result.duelistWinner);
    }
}
