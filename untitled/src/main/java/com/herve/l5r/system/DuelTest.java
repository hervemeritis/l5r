package com.herve.l5r.system;

import com.herve.l5r.system.model.*;
import com.herve.l5r.system.roll.RollAndKeepDiceSystem;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;
import com.herve.l5r.system.roll.model.competence.IajustsuEvaluationRollFactory;
import com.herve.l5r.system.roll.model.competence.IajutsuConcentrationRollFactory;
import com.herve.l5r.system.roll.model.competence.IajutsuFrappeRollFactory;

import java.util.Set;

public class DuelTest {
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
                                .competences(Set.of(Competence.schoolCompetence().name(CompetenceName.IAJUSTSU).value(3).emphasis(Set.of(Emphasis.CONCENTRATION))))
                                .build();
        IajustsuEvaluationRollFactory iajustsuEvaluationRollFactory = new IajustsuEvaluationRollFactory();
        IajutsuConcentrationRollFactory concentrationRollFactory = new IajutsuConcentrationRollFactory();
        IajutsuFrappeRollFactory iajutsuFrappeRollFactory = new IajutsuFrappeRollFactory();
        RollAndKeepRequest frappeRequest = iajutsuFrappeRollFactory.generate(kakita);
        RollAndKeepRequest evaluationRequest = iajustsuEvaluationRollFactory.generate(kakita);
        RollAndKeepRequest concentrationRequest = concentrationRollFactory.generate(kakita);
        RollAndKeepDiceSystem rollAndKeepDiceSystem = new RollAndKeepDiceSystem(10);
        System.out.println("Evaluation -> " + rollAndKeepDiceSystem.rollAndKeep(evaluationRequest).maxValue());
        System.out.println("Concentration -> " + rollAndKeepDiceSystem.rollAndKeep(concentrationRequest).maxValue());
        System.out.println("Frappe -> " + rollAndKeepDiceSystem.rollAndKeep(frappeRequest).maxValue());
    }
}
