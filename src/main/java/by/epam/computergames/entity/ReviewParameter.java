package by.epam.computergames.entity;

public class ReviewParameter
{
    private String idGame;
    private String pageNumber;
    private String command;

    public String getIdGame() {
        return idGame;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber)
    {
        this.pageNumber=String.valueOf(pageNumber);
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
