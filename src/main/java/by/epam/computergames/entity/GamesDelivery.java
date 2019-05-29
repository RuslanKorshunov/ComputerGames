package by.epam.computergames.entity;

import java.util.List;

public class GamesDelivery
{
    private int pageNumber;
    private List<Game> games;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
