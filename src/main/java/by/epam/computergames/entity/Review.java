package by.epam.computergames.entity;

public class Review
{
    private int mark;
    private String login;
    private long idGame;
    private String comments;

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getIdGame() {
        return idGame;
    }

    public void setIdGame(long idGame) {
        this.idGame = idGame;
    }

    public String getComment() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
