package by.epam.computergames.entity;

public class Developer extends AbstractEntity{
    private long idDeveloper;
    private String name;

    public long getIdDeveloper() {
        return idDeveloper;
    }

    public void setIdDeveloper(long idDeveloper) {
        this.idDeveloper = idDeveloper;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}