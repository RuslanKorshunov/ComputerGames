package by.epam.computergames.entity;

public enum UserEnum
{
    ADMIN(1),
    USER(2),
    GUEST(3);

    int id;

    UserEnum(int id)
    {
        this.id=id;
    }

    public int getId()
    {
        return id;
    }
}
