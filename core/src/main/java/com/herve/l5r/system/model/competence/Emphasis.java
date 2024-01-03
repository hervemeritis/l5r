package com.herve.l5r.system.model.competence;

public enum Emphasis {
    KATANA("Katana"),
    EVALUATION("Evaluation"),
    CONCENTRATION("Concentration"),
    NO_EMPHASIS_KNOWN("");

    private final String emphasisName;

    Emphasis(String emphasisName) {
        this.emphasisName = emphasisName;
    }

    public String emphasisName() {
        return emphasisName;
    }
}
