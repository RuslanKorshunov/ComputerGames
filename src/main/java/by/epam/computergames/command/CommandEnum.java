package by.epam.computergames.command;

public enum CommandEnum
{
    AUTHORIZATION("authorization"),
    LOGOUT("logout"),
    GET_USER_INFO("get_user_info"),
    CHANGE_NAME("change_name"),
    CHANGE_SURNAME("change_surname"),
    CHANGE_PASSWORD("change_password"),
    CHANGE_EMAIL("change_email"),
    CHANGE_SEX("change_sex"),
    GET_MAIN_PAGE("get_main_page"),
    REGISTRATION("registration"),
    GET_REGISTRATION_PAGE("get_registration_page"),
    GET_GAMES("get_games"),
    GET_GAME("get_game");

    private String value;

    CommandEnum(String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
