package by.epam.computergames.entity;

public class Game {
    private String idGame;
    private String name;
    private Genre genre;
    private String developer;
    private String picture;
    private String year;
    private String information;

    public String getIdGame() {
        return idGame;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
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

    public void setGenre(int idGenre) {
        genre = Genre.getGenre(idGenre);
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "Game{" +
                "idGame=" + idGame +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", developer='" + developer + '\'' +
                ", picture='" + picture + '\'' +
                ", year=" + year +
                ", information='" + information + '\'' +
                '}';
    }
}