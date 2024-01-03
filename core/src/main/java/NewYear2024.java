public class NewYear2024 {

    public DirectionExpertise meritis;

    public GreatPlaceToWorkForDev newYear2024() {
        return meritis.directionExpertise("Software")
                      .directeur("Gaetan Eleouet")
                      .practices("Langage", "Architecte & Techlead", "Craftmanship", "Algo")
                      .leaders("Maroua DHIFLI", "Anis SMAIL", "Pierre SIEPKA", "Hervé LETOURNEUR", "Alban CLEVY", "Aquiles TORRES ALVAREZ")
                      .build();
    }

    record GreatPlaceToWorkForDev() {}

    public interface DirectionExpertise {
        Directeur directionExpertise(String direction);
    }

    public interface Directeur {
        Practices directeur(String name);
    }

    public interface Practices {
        Leader practices(String... practices);
    }

    public interface Leader {
        GreatPlaceToWorkBuilder leaders(String... names);
    }

    public interface GreatPlaceToWorkBuilder {
        GreatPlaceToWorkForDev build();
    }
}
