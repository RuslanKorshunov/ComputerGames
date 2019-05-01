package by.epam.computergames.entity;

public class Game
{
    private long idGames;
    private String name;
    private Genre genre;
    private Developer developer;

    public long getIdGames() {
        return idGames;
    }

    public void setIdGames(long idGames) {
        this.idGames = idGames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public long getIdDeveloper() {
        return developer.getIdDeveloper();
    }
}
