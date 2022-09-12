package com.herve.l5r.system;

import com.herve.l5r.system.model.*;
import com.herve.l5r.system.model.avantage.Avantage;
import com.herve.l5r.system.model.competence.CompetenceTypeRoll;
import com.herve.l5r.system.model.school.FamilySchool;
import com.herve.l5r.system.model.school.School;
import com.herve.l5r.system.roll.RollAndKeepDiceSystem;
import com.herve.l5r.system.roll.model.RollAndKeepRequest;
import com.herve.l5r.system.roll.model.competence.CompetenceRollContext;

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
                                .competences(Set.of(Competence.schoolCompetence().name(CompetenceName.IAJUSTSU).value(3).emphasis(Set.of(Emphasis.CONCENTRATION))))
                                .avantages(Set.of(Avantage.VIRTUOSE))
                                .schools(List.of(School.of(FamilySchool.KAKITA_BUSHI_SCHOOL, 1)))
                                .build();
        RollAndKeepRequest evaluationRequest = CompetenceRollContext.of(CompetenceTypeRoll.IAJUTSU_EVALUATION, kakita).generateRollRequest();
        RollAndKeepRequest concentrationRequest = CompetenceRollContext.of(CompetenceTypeRoll.IAJUTSU_CONCENTRATION, kakita).generateRollRequest();
        RollAndKeepRequest frappeRequest = CompetenceRollContext.of(CompetenceTypeRoll.IAJUTSU_FRAPPE, kakita).generateRollRequest();
        RollAndKeepDiceSystem rollAndKeepDiceSystem = new RollAndKeepDiceSystem(10);
        System.out.println("Evaluation -> " + rollAndKeepDiceSystem.rollAndKeep(evaluationRequest).maxValue());
        System.out.println("Concentration -> " + rollAndKeepDiceSystem.rollAndKeep(concentrationRequest).maxValue());
        System.out.println("Frappe -> " + rollAndKeepDiceSystem.rollAndKeep(frappeRequest).maxValue());
    }
}
