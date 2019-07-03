package by.epam.computergames.entity;

public class GameParameter {
    private String pageNumber;
    private String yearFrom;
    private String yearTo;
    private String idGenre;
    private String idDeveloper;
    private String command;

    public GameParameter(String pageNumber,
                         String yearFrom,
                         String yearTo,
                         String idGenre,
                         String idDeveloper,
                         String command) {
        this.pageNumber = pageNumber;
        this.yearFrom = yearFrom;
        this.yearTo = yearTo;
        this.idGenre = idGenre;
        this.idDeveloper = idDeveloper;
        this.command = command;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = String.valueOf(pageNumber);
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(int yearFrom) {
        this.yearFrom = String.valueOf(yearFrom);
    }

    public void setYearFrom(String yearFrom) {
        this.yearFrom = yearFrom;
    }

    public String getYearTo() {
        return yearTo;
    }

    public void setYearTo(int yearTo) {
        this.yearTo = String.valueOf(yearTo);
    }

    public void setYearTo(String yearTo) {
        this.yearTo = yearTo;
    }

    public String getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(String idGenre) {
        this.idGenre = idGenre;
    }

    public String getIdDeveloper() {
        return idDeveloper;
    }

    public void setIdDeveloper(String idDeveloper) {
        this.idDeveloper = idDeveloper;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}