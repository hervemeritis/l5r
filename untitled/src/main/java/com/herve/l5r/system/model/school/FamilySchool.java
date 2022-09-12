package com.herve.l5r.system.model.school;

import com.herve.l5r.system.model.school.RankSchool;
import com.herve.l5r.system.model.school.TypeSchool;
import com.herve.l5r.system.model.school.grue.kakita.rank.LaVoieDeLaGrue;

import java.util.Set;

public enum FamilySchool {
    KAKITA_BUSHI_SCHOOL("Kakita Duelist Bushi", TypeSchool.BUSHI, Set.of(new LaVoieDeLaGrue()));

    public final String name;
    public final TypeSchool type;
    public final Set<RankSchool> ranks;

    FamilySchool(String name, TypeSchool type, Set<RankSchool> ranks) {
        this.name = name;
        this.type = type;
        this.ranks = ranks;
    }
}
