package com.herve.l5r.system.model.school.lion.matsu;

import com.herve.l5r.system.model.competence.CompetenceName;
import com.herve.l5r.system.model.competence.Emphasis;
import com.herve.l5r.system.model.school.RankSchool;
import com.herve.l5r.system.model.school.SchoolDefintion;
import com.herve.l5r.system.model.school.TypeSchool;
import com.herve.l5r.system.model.school.lion.matsu.rank.HurlementDuLion;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

public class MatsuBerserkerSchool implements SchoolDefintion {
    @Override
    public String name() {
        return "Matsu Berserker";
    }

    @Override
    public TypeSchool type() {
        return TypeSchool.BUSHI;
    }

    @Override
    public Set<RankSchool> ranks() {
        return Set.of(new HurlementDuLion());
    }

    @Override
    public Set<CompetenceName> schoolCompetence() {
        return Set.of(CompetenceName.KENJUTSU, CompetenceName.BATAILLE, CompetenceName.KYUJUTSU, CompetenceName.JIUJITSU, CompetenceName.CONN_HISTOIRE);
    }

    @Override
    public Function<CompetenceName, Set<Emphasis>> baseEmphasis() {
        return cpt -> cpt.equals(CompetenceName.KENJUTSU) ? Set.of(Emphasis.KATANA) : Collections.emptySet();
    }
}
