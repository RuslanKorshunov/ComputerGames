package by.epam.computergames.command;

public enum CommandEnum
{
    AUTHORIZATION("authorization");

    private String value;

    CommandEnum(String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
