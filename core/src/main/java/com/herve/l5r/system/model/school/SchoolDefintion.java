package com.herve.l5r.system.model.school;

import com.herve.l5r.system.model.competence.Competence;
import com.herve.l5r.system.model.competence.CompetenceName;
import com.herve.l5r.system.model.competence.Emphasis;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface SchoolDefintion {
    String schoolName();

    TypeSchool type();

    Set<RankSchool> ranks();

    Set<CompetenceName> schoolCompetence();

    Function<CompetenceName, Set<Emphasis>> baseEmphasis();

    default Set<Competence.LinkedSamurai> generateSchoolCompetence() {
        return schoolCompetence().stream()
                                 .map(cptName -> Competence.schoolCompetence().name(cptName).value(1).emphasis(baseEmphasis().apply(cptName)))
                                 .collect(Collectors.toSet());
    }
}
