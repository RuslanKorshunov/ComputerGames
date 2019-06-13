package by.epam.computergames.command;

public enum CommandConst
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
    GET_GAME("get_game"),
    BACKWARD("backward"),
    FORWARD("forward"),
    GET_SEARCH_PAGE("get_search_page"),
    CHANGE_SEARCH_PARAMETER("change_search_parameter"),
    GET_REVIEW_PAGE("get_review_page"),
    SET_REVIEW("set_review");

    private String value;

    CommandConst(String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
