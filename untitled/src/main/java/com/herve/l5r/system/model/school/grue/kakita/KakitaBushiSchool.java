package com.herve.l5r.system.model.school.grue.kakita;

import com.herve.l5r.system.model.competence.CompetenceName;
import com.herve.l5r.system.model.competence.Emphasis;
import com.herve.l5r.system.model.school.RankSchool;
import com.herve.l5r.system.model.school.SchoolDefintion;
import com.herve.l5r.system.model.school.TypeSchool;
import com.herve.l5r.system.model.school.grue.kakita.rank.LaVoieDeLaGrue;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

import static com.herve.l5r.system.model.competence.CompetenceName.*;

public class KakitaBushiSchool implements SchoolDefintion {

    private final Set<RankSchool> ranks = Set.of(new LaVoieDeLaGrue());
    private final Set<CompetenceName> schoolCompetence = Set.of(IAIJUSTSU, ETIQUETTE, KENJUTSU, CEREONIE_DU_THE, KYUJUTSU, SINCERITE);

    private final Function<CompetenceName, Set<Emphasis>> baseEmphasis = (cpt) -> cpt.equals(IAIJUSTSU) ? Set.of(Emphasis.CONCENTRATION) : Collections.emptySet();

    @Override
    public String name() {
        return "Kakita Duelist Bushi";
    }

    @Override
    public TypeSchool type() {
        return TypeSchool.BUSHI;
    }

    @Override
    public Set<RankSchool> ranks() {
        return ranks;
    }

    @Override
    public Set<CompetenceName> schoolCompetence() {
        return schoolCompetence;
    }

    @Override
    public Function<CompetenceName, Set<Emphasis>> baseEmphasis() {
        return baseEmphasis;
    }


}
