package by.epam.computergames.command;

public enum ConstEnum
{
    LOGIN("login"),
    PASSWORD("password"),
    ROLE("role"),
    NAME("name"),
    SURNAME("surname"),
    SEX("sex"),
    EMAIL("email");

    String value;

    ConstEnum(String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
