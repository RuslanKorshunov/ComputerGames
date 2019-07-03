package by.epam.computergames.command;

public enum CommandName {
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
    BACKWARD_GAMES("backward_games"),
    FORWARD_GAMES("forward_games"),
    FORWARD_REVIEWS("forward_reviews"),
    BACKWARD_REVIEWS("backward_reviews"),
    GET_SEARCH_PAGE("get_search_page"),
    CHANGE_SEARCH_PARAMETER("change_search_parameter"),
    GET_REVIEW_PAGE("get_review_page"),
    SET_REVIEW("set_review"),
    CHANGE_LANG("change_lang"),
    GET_AUTHORIZATION_PAGE("get_authorization_page"),
    GET_REVIEWS_PAGE("get_reviews_page"),
    DELETE_REVIEW("delete_review");

    private String value;

    CommandName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
