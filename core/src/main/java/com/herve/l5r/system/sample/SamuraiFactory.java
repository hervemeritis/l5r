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
                                .name("Iniro")
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

    public static Samurai mirumoto() {
        Samurai mirumoto = Samurai.builder()
                                .statut(1.0d)
                                .honneur(4.5d)
                                .gloire(1.0d)
                                .attributs(Attribut.builder()
                                                   .agilite(3)
                                                   .intelligence(2)
                                                   .reflexe(3)
                                                   .intuition(3)
                                                   .force(2)
                                                   .perception(2)
                                                   .constitution(3)
                                                   .volonte(3)
                                                   .vide(2)
                                                   .build())
                                .family(Family.MIRUMOTO)
                                .name("Ujia")
                                .competences(FamilySchool.MIRUMOTO_BUSHI_SCHOOl.generateSchoolCompetence())
                                .avantages(Set.of(Avantage.AME_D_ARTISTE, Avantage.FORCE_DE_LA_TERRE))
                                .schools(List.of(School.of(FamilySchool.MIRUMOTO_BUSHI_SCHOOl, 1)))
                                .build();
        mirumoto.retrieveCompetence(CompetenceName.IAIJUSTSU).ifPresent(Competence::incrementValue);
        mirumoto.retrieveCompetence(CompetenceName.KENJUTSU).ifPresent(cpt -> cpt.incrementValue(2));
        mirumoto.retrieveCompetence(CompetenceName.ETIQUETTE).ifPresent(Competence::incrementValue);
        mirumoto.add(Competence.schoolCompetence().name(CompetenceName.CALLIGRAPHIE).value(2).noEmphasis().samurai(mirumoto));
        mirumoto.add(Competence.builder().name(CompetenceName.ATHLETISME).value(2).noEmphasis().samurai(mirumoto));
        mirumoto.add(Competence.builder().name(CompetenceName.POESIE).value(2).noEmphasis().samurai(mirumoto));
        mirumoto.add(Competence.builder().name(CompetenceName.ENQUETE).value(2).noEmphasis().samurai(mirumoto));
        mirumoto.add(Competence.builder().name(CompetenceName.KYUJUTSU).value(1).noEmphasis().samurai(mirumoto));
        mirumoto.add(Competence.builder().name(CompetenceName.ETIQUETTE).value(1).noEmphasis().samurai(mirumoto));
        mirumoto.add(Competence.builder().name(CompetenceName.CONN_HERALDIQUE).value(1).noEmphasis().samurai(mirumoto));
        return mirumoto;
    }

    public static Samurai matsu() {
        Samurai mastu = Samurai.builder()
                                  .statut(1.0d)
                                  .honneur(6.5d)
                                  .gloire(1.0d)
                                  .attributs(Attribut.builder()
                                                     .agilite(3)
                                                     .intelligence(2)
                                                     .reflexe(2)
                                                     .intuition(2)
                                                     .force(4)
                                                     .perception(2)
                                                     .constitution(3)
                                                     .volonte(3)
                                                     .vide(2)
                                                     .build())
                                  .family(Family.MATSU)
                                  .name("Aoka")
                                  .competences(FamilySchool.MATSU_BERSERKER_SCHOOL.generateSchoolCompetence())
                                  .avantages(Set.of(Avantage.GRAND, Avantage.FORCE_DE_LA_TERRE))
                                  .schools(List.of(School.of(FamilySchool.MATSU_BERSERKER_SCHOOL, 1)))
                                  .build();
        mastu.retrieveCompetence(CompetenceName.KENJUTSU).ifPresent(cpt -> cpt.incrementValue(2));
        mastu.add(Competence.schoolCompetence().name(CompetenceName.IAIJUSTSU).value(1).noEmphasis().samurai(mastu));
        mastu.add(Competence.schoolCompetence().name(CompetenceName.LANCE).value(2).noEmphasis().samurai(mastu));
        mastu.add(Competence.builder().name(CompetenceName.ATHLETISME).value(2).noEmphasis().samurai(mastu));
        mastu.add(Competence.builder().name(CompetenceName.ETIQUETTE).value(1).noEmphasis().samurai(mastu));
        mastu.add(Competence.builder().name(CompetenceName.EQUITATION).value(1).noEmphasis().samurai(mastu));
        mastu.add(Competence.builder().name(CompetenceName.CONN_HERALDIQUE).value(1).noEmphasis().samurai(mastu));
        mastu.retrieveCompetence(CompetenceName.JIUJITSU).ifPresent(Competence::incrementValue);
        return mastu;
    }
}
