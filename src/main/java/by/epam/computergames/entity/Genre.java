package by.epam.computergames.entity;

public enum Genre
{
    LIFE_SIMULATION(1, "life_simulation");

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
}
