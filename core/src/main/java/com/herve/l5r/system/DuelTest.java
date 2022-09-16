package com.herve.l5r.system;

import com.herve.l5r.system.model.*;
import com.herve.l5r.system.model.avantage.Avantage;
import com.herve.l5r.system.model.competence.CompetenceName;
import com.herve.l5r.system.model.school.FamilySchool;
import com.herve.l5r.system.model.school.School;
import com.herve.l5r.system.scene.combat.iaijutsu.DuelResult;
import com.herve.l5r.system.scene.combat.iaijutsu.IaijutsuDuel;

import java.util.List;
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
                                .competences(FamilySchool.KAKITA_BUSHI_SCHOOL.generateSchoolCompetence())
                                .avantages(Set.of(Avantage.VIRTUOSE))
                                .schools(List.of(School.of(FamilySchool.KAKITA_BUSHI_SCHOOL, 1)))
                                .build();
        kakita.competences.get(CompetenceName.IAIJUSTSU).incrementValue(2);
        kakita.retrieveCompetence(CompetenceName.KENJUTSU).ifPresent(cpt -> cpt.incrementValue(2));
        Samurai kakita2 = Samurai.builder()
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
                                 .name("INIRO 2")
                                 .competences(FamilySchool.KAKITA_BUSHI_SCHOOL.generateSchoolCompetence())
                                 .avantages(Set.of(Avantage.VIRTUOSE))
                                 .schools(List.of(School.of(FamilySchool.KAKITA_BUSHI_SCHOOL, 1)))
                                 .build();
        kakita2.competences.get(CompetenceName.IAIJUSTSU).incrementValue(2);
        kakita2.retrieveCompetence(CompetenceName.KENJUTSU).ifPresent(cpt -> cpt.incrementValue(2));
//        System.out.println(kakita.rollInitiative());
//        RollAndKeepRequest evaluationRequest = CompetenceRollContext.of(CompetenceTypeRoll.IAJUTSU_EVALUATION, kakita).generateCompetenceRollRequest();
//        RollAndKeepRequest concentrationRequest = CompetenceRollContext.of(CompetenceTypeRoll.IAJUTSU_CONCENTRATION, kakita).generateCompetenceRollRequest();
//        RollAndKeepRequest frappeRequest = CompetenceRollContext.of(CompetenceTypeRoll.IAJUTSU_FRAPPE, kakita).generateCompetenceRollRequest();
//        RollAndKeepDiceSystem rollAndKeepDiceSystem = new RollAndKeepDiceSystem(10);
//        System.out.println("Evaluation -> " + rollAndKeepDiceSystem.rollAndKeep(evaluationRequest).maxValue());
//        System.out.println("Concentration -> " + rollAndKeepDiceSystem.rollAndKeep(concentrationRequest).maxValue());
//        System.out.println("Frappe -> " + rollAndKeepDiceSystem.rollAndKeep(frappeRequest).maxValue());
        DuelResult result = IaijutsuDuel.between(kakita, kakita2)
                                        .rollInitiative()
                                        .evaluation()
                                        .concentration()
                                        .frappe();
        result.eventLogger().print();
        System.out.println("Winner is " + result.duelistWinner);
    }
}
