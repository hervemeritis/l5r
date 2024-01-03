package com.herve.l5r.system.model.competence;

public enum CompetenceName {
    IAIJUSTSU("Iaijutsu"),
    KENJUTSU("Kenjutsu"),
    KYUJUTSU("Kyujutsu"),
    SINCERITE("Sincérité"),
    ETIQUETTE("Etiquette"),
    CEREMONIE_DU_THE("Cérémonie du thé"),
    COURTISAN("Courtisan"),
    POESIE("Poésie"),
    CONN_BUSHIDO("Connaissance du Bushido"),
    EQUITATION("Equitation"),
    CONN_HERALDIQUE("Connaissance Héraldique"),
    DEFENSE("Défense"),
    CONN_SHUGENJA("Connaissance des Shugenja"),
    MEDITATION("Meditation"),
    THEOLOGY("Theologie"),
    CALLIGRAPHIE("Calligraphie"),
    ATHLETISME("Athtlétisme"),
    ENQUETE("Enquête"),
    BATAILLE("Bataille"),
    JIUJITSU("Jiujitsu"),
    CONN_HISTOIRE("Connaissance histoire"),
    LANCE("Lance");

    private final String competenceName;

    CompetenceName(String competenceName) {
        this.competenceName = competenceName;
    }

    public String competenceName() {
        return competenceName;
    }

}
