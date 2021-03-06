package by.epam.computergames.entity;

public enum Genre {
    LIFE_SIMULATION(1, "life_simulation"),
    INTERACTIVE_MOVIE(2, "interactive_movie"),
    RPG(3, "rpg"),
    UNKNOWN(0, "unknown");

    private int idGenre;
    private String value;

    Genre(int idGenre, String value) {
        this.idGenre = idGenre;
        this.value = value;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public String getValue() {
        return value;
    }

    public static Genre getGenre(int idGenre) {
        Genre genre;
        switch (idGenre) {
            case 1:
                genre = LIFE_SIMULATION;
                break;
            case 2:
                genre = INTERACTIVE_MOVIE;
                break;
            case 3:
                genre = RPG;
                break;
            default:
                genre = UNKNOWN;
        }
        return genre;
    }
}