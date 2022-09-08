package com.herve.l5r.system.model;

public enum Family {
    KAKITA("Kakita", Clan.GRUE),
    MIRUMOTO("Mirumoto", Clan.DRAGON),
    MATSU("Matsu", Clan.LION);

    public final String familyName;
    public final Clan clan;

    Family(String familyName, Clan clan) {
        this.familyName = familyName;
        this.clan = clan;
    }

}
