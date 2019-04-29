package by.epam.computergames.command;

public enum Table
{
    USERS("users"),
    USER_INFO("userinfo");

    private String value;

    Table(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
