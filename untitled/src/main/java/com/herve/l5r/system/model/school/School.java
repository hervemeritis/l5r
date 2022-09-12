package com.herve.l5r.system.model.school;

import java.util.stream.Stream;

public class School {
    public final int rank;
    private final FamilySchool familySchool;

    private School(int rank, FamilySchool familySchool) {
        this.rank = rank;
        this.familySchool = familySchool;
    }

    public static School of(FamilySchool school, int rank) {
        return new School(rank, school);
    }

    public Stream<RankSchool> availableRank() {
        return familySchool.ranks.stream()
                                 .takeWhile(rankSchool -> rankSchool.rank() <= rank);
    }


}
