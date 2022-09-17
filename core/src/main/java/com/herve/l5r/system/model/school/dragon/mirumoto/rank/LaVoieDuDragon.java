package com.herve.l5r.system.model.school.dragon.mirumoto.rank;

import com.herve.l5r.system.model.school.RankSchool;

public class LaVoieDuDragon implements RankSchool {
    @Override
    public int rank() {
        return 1;
    }

    @Override
    public String name() {
        return "La voie du dragon";
    }
}
