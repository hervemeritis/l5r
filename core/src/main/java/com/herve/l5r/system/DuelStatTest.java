package com.herve.l5r.system;

import com.herve.l5r.system.roll.model.Counter;
import com.herve.l5r.system.sample.SamuraiFactory;
import com.herve.l5r.system.scene.combat.iaijutsu.DuelResult;
import com.herve.l5r.system.scene.combat.iaijutsu.IaijutsuDuel;

import java.util.HashMap;
import java.util.Map;

public class DuelStatTest {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Map<String, Integer> duelStat = new HashMap<>();
        do {
            DuelResult result = IaijutsuDuel.between(SamuraiFactory.mirumoto(), SamuraiFactory.matsu())
                                            .rollInitiative()
                                            .evaluation()
                                            .concentration()
                                            .frappe();
            duelStat.merge(result.duelistWinner, 1, Integer::sum);
            counter.getAndIncrement();
        } while (counter.value() < 1_000_000);
        duelStat.forEach((s,i) -> System.out.println(s + " " +  (double)i / (double)counter.value() * 100));
    }
}
