package by.epam.computergames.command;

public enum CommandEnum
{
    AUTHORIZATION("authorization"),
    LOGOUT("logout"),
    GET_USER_INFO("get_user_info");

    private String value;

    CommandEnum(String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
