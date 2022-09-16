package com.herve.l5r.system.model;

import com.herve.l5r.system.model.avantage.Avantage;
import com.herve.l5r.system.model.competence.Competence;
import com.herve.l5r.system.model.competence.CompetenceName;
import com.herve.l5r.system.model.competence.Emphasis;

import java.util.Set;

public class SamuraiFactory {

    static Samurai kakitaRank1() {
        return Samurai.builder()
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
                      .competences(Set.of(Competence.schoolCompetence().name(CompetenceName.IAIJUSTSU).value(3).emphasis(Set.of(Emphasis.CONCENTRATION))))
                      .avantages(Set.of(Avantage.VIRTUOSE))
                      .build();
    }

    static Samurai kakitaRank2() {
        return Samurai.builder()
                      .statut(1.0d)
                      .honneur(6.5d)
                      .gloire(1.0d)
                      .attributs(Attribut.builder()
                                         .agilite(4)
                                         .intelligence(4)
                                         .reflexe(3)
                                         .intuition(3)
                                         .force(3)
                                         .perception(3)
                                         .constitution(3)
                                         .volonte(3)
                                         .vide(4)
                                         .build())
                      .family(Family.KAKITA)
                      .name("INIRO")
                      .competences(Set.of(Competence.schoolCompetence().name(CompetenceName.IAIJUSTSU).value(4).emphasis(Set.of(Emphasis.CONCENTRATION))))
                      .avantages(Set.of(Avantage.VIRTUOSE))
                      .build();
    }


    static Samurai kakitaRank3() {
        return Samurai.builder()
                      .statut(1.0d)
                      .honneur(6.5d)
                      .gloire(1.0d)
                      .attributs(Attribut.builder()
                                         .agilite(4)
                                         .intelligence(4)
                                         .reflexe(3)
                                         .intuition(3)
                                         .force(3)
                                         .perception(3)
                                         .constitution(3)
                                         .volonte(3)
                                         .vide(4)
                                         .build())
                      .family(Family.KAKITA)
                      .name("INIRO")
                      .competences(Set.of(Competence.schoolCompetence().name(CompetenceName.IAIJUSTSU).value(5).emphasis(Set.of(Emphasis.CONCENTRATION))))
                      .avantages(Set.of(Avantage.VIRTUOSE))
                      .build();
    }

}
