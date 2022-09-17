package com.herve.l5r.system.sample;

import com.herve.l5r.system.model.Attribut;
import com.herve.l5r.system.model.Family;
import com.herve.l5r.system.model.Samurai;
import com.herve.l5r.system.model.avantage.Avantage;
import com.herve.l5r.system.model.competence.Competence;
import com.herve.l5r.system.model.competence.CompetenceName;
import com.herve.l5r.system.model.competence.Emphasis;
import com.herve.l5r.system.model.school.FamilySchool;
import com.herve.l5r.system.model.school.School;

import java.util.List;
import java.util.Set;

public class SamuraiFactory {

    public static Samurai kakita() {
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
                                .avantages(Set.of(Avantage.VIRTUOSE, Avantage.FORCE_DE_LA_TERRE))
                                .schools(List.of(School.of(FamilySchool.KAKITA_BUSHI_SCHOOL, 1)))
                                .build();
        kakita.retrieveCompetence(CompetenceName.IAIJUSTSU).ifPresent(cpt -> cpt.incrementValue(2));
        kakita.retrieveCompetence(CompetenceName.KENJUTSU).ifPresent(cpt -> cpt.incrementValue(2));
        kakita.retrieveCompetence(CompetenceName.KENJUTSU).ifPresent(cpt -> cpt.addEmphasis(Emphasis.KATANA));
        kakita.retrieveCompetence(CompetenceName.ETIQUETTE).ifPresent(Competence::incrementValue);
        kakita.add(Competence.schoolCompetence().name(CompetenceName.COURTISAN).value(1).noEmphasis().samurai(kakita));
        kakita.add(Competence.builder().name(CompetenceName.EQUITATION).value(1).noEmphasis().samurai(kakita));
        kakita.add(Competence.builder().name(CompetenceName.CONN_BUSHIDO).value(1).noEmphasis().samurai(kakita));
        kakita.add(Competence.builder().name(CompetenceName.CONN_HERALDIQUE).value(1).noEmphasis().samurai(kakita));
        kakita.add(Competence.builder().name(CompetenceName.POESIE).value(1).noEmphasis().samurai(kakita));
        return kakita;
    }
}
