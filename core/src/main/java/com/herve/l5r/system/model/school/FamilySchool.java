package com.herve.l5r.system.model.school;

import com.herve.l5r.system.model.competence.CompetenceName;
import com.herve.l5r.system.model.competence.Emphasis;
import com.herve.l5r.system.model.school.dragon.mirumoto.MirumotoBushiSchool;
import com.herve.l5r.system.model.school.grue.kakita.KakitaBushiSchool;
import com.herve.l5r.system.model.school.lion.matsu.MatsuBerserkerSchool;

import java.util.Set;
import java.util.function.Function;

public enum FamilySchool implements SchoolDefintion {
    KAKITA_BUSHI_SCHOOL(new KakitaBushiSchool()),
    MIRUMOTO_BUSHI_SCHOOl(new MirumotoBushiSchool()),
    MATSU_BERSERKER_SCHOOL(new MatsuBerserkerSchool());

    public final SchoolDefintion schoolDefintion;

    FamilySchool(SchoolDefintion schoolDefintion) {
        this.schoolDefintion = schoolDefintion;
    }

    @Override
    public TypeSchool type() {
        return schoolDefintion.type();
    }

    @Override
    public Set<RankSchool> ranks() {
        return schoolDefintion.ranks();
    }

    @Override
    public Set<CompetenceName> schoolCompetence() {
        return schoolDefintion.schoolCompetence();
    }

    @Override
    public Function<CompetenceName, Set<Emphasis>> baseEmphasis() {
        return schoolDefintion.baseEmphasis();
    }
}
