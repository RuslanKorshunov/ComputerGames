package by.epam.computergames.entity;

public enum Role {
    ADMIN(1),
    USER(2),
    GUEST(3);

    int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}