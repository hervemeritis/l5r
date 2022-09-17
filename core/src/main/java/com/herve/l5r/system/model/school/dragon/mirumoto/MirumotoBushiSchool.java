package com.herve.l5r.system.model.school.dragon.mirumoto;

import com.herve.l5r.system.model.competence.CompetenceName;
import com.herve.l5r.system.model.competence.Emphasis;
import com.herve.l5r.system.model.school.RankSchool;
import com.herve.l5r.system.model.school.SchoolDefintion;
import com.herve.l5r.system.model.school.TypeSchool;
import com.herve.l5r.system.model.school.dragon.mirumoto.rank.LaVoieDuDragon;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

public class MirumotoBushiSchool implements SchoolDefintion {
    @Override
    public String name() {
        return "Mirumoto Bushi School";
    }

    @Override
    public TypeSchool type() {
        return TypeSchool.BUSHI;
    }

    @Override
    public Set<RankSchool> ranks() {
        return Set.of(new LaVoieDuDragon());
    }

    @Override
    public Set<CompetenceName> schoolCompetence() {
        return Set.of(CompetenceName.KENJUTSU, CompetenceName.IAIJUSTSU, CompetenceName.DEFENSE,CompetenceName.CONN_SHUGENJA, CompetenceName.MEDITATION, CompetenceName.THEOLOGY);
    }

    @Override
    public Function<CompetenceName, Set<Emphasis>> baseEmphasis() {
        return cptName -> cptName.equals(CompetenceName.KENJUTSU) ? Set.of(Emphasis.KATANA) : Collections.emptySet();
    }
}
