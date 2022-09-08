package com.herve.l5r.system;

import com.herve.l5r.system.model.*;
import com.herve.l5r.system.roll.RollAndKeepDiceSystem;
import com.herve.l5r.system.roll.model.competence.IajustsuEvaluationRollFactory;

import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Samurai kakita = Samurai.builder()
                                .statut(1.0d)
                                .honneur(6.5d)
                                .gloire(1.0d)
                                .attributs(Attribut.builder()
                                                   .agilite(3)
                                                   .intelligence(2)
                                                   .reflexe(4)
                                                   .intuition(2)
                                                   .force(2)
                                                   .perception(2)
                                                   .constitution(2)
                                                   .volonte(2)
                                                   .vide(3)
                                                   .build())
                                .family(Family.KAKITA)
                                .name("INIRO")
                                .competences(Set.of(Competence.builder().name(CompetenceName.IAJUSTSU).value(3).emphasis(Set.of(Emphasis.CONCENTRATION))))
                                .build();
        IajustsuEvaluationRollFactory iajustsuEvaluationRollFactory = new IajustsuEvaluationRollFactory();
        RollAndKeepRequest request = iajustsuEvaluationRollFactory.generate(kakita);
        System.out.println(new RollAndKeepDiceSystem().rollAndKeep(request).maxValue());
    }
}
