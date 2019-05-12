package by.epam.computergames.entity;

import by.epam.computergames.exception.IncorrectDataException;

public class Game
{
    private static final String PATH="webapp/img/";
    private static final String NOT_FOUND="not_found.png";

    private long idGame;
    private String name;
    private Genre genre;
    private String developer;
    private String picture;
    private int year;

    public long getIdGame() {
        return idGame;
    }

    public void setIdGame(long idGame) {
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

    public void setGenre(int idGenre) throws IncorrectDataException
    {
        genre=Genre.getGenre(idGenre);
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

    public void setPicture(String picture)
    {
        //TODO сделать проверку на наличие картинки
/*        File file=new File(PATH+picture);
        if(file.exists())
        {*/
            this.picture = picture;
/*        }
        else
        {
            this.picture=NOT_FOUND;
        }*/
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Game{" +
                "idGame=" + idGame +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", developer='" + developer + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
