package by.epam.computergames.command;

public enum CommandName {
    AUTHORIZATION("authorization", new AuthorizationCommand()),
    LOGOUT("logout", new LogOutCommand()),
    GET_USER_INFO("get_user_info", new GetUserInfoCommand()),
    CHANGE_NAME("change_name", new ChangeUserParameterCommand()),
    CHANGE_SURNAME("change_surname", new ChangeUserParameterCommand()),
    CHANGE_PASSWORD("change_password", new ChangeUserParameterCommand()),
    CHANGE_EMAIL("change_email", new ChangeUserParameterCommand()),
    CHANGE_SEX("change_sex", new ChangeUserParameterCommand()),
    GET_MAIN_PAGE("get_main_page", new GetMainPageCommand()),
    REGISTRATION("registration", new RegistrationCommand()),
    GET_REGISTRATION_PAGE("get_registration_page", new GetRegistrationPageCommand()),
    GET_GAMES("get_games", new SearchGamesCommand()),
    GET_GAME("get_game", new GetGameCommand()),
    BACKWARD_GAMES("backward_games", new SearchGamesCommand()),
    FORWARD_GAMES("forward_games", new SearchGamesCommand()),
    FORWARD_REVIEWS("forward_reviews", new GetReviewsPageCommand()),
    BACKWARD_REVIEWS("backward_reviews", new GetReviewsPageCommand()),
    GET_SEARCH_PAGE("get_search_page", new GetSearchPageCommand()),
    CHANGE_SEARCH_PARAMETER("change_search_parameter", new ChangeSearchParameterCommand()),
    GET_REVIEW_PAGE("get_review_page", new GetReviewPageCommand()),
    SET_REVIEW("set_review", new SetReviewCommand()),
    CHANGE_LANG("change_lang", new ChangeLanguageCommand()),
    GET_AUTHORIZATION_PAGE("get_authorization_page", new GetAuthorizationPageCommand()),
    GET_REVIEWS_PAGE("get_reviews_page", new GetReviewsPageCommand()),
    DELETE_REVIEW("delete_review", new DeleteReviewCommand()),
    GET_CHANGE_GAME_PAGE("get_change_game_page", new GetChangeGamePageCommand()),
    CHANGE_GAME("change_game", new ChangeGameCommand());

    private String value;

    private AbstractCommand command;

    CommandName(String value, AbstractCommand command) {
        this.value = value;
        this.command = command;
    }

    public String getValue() {
        return value;
    }

    public AbstractCommand getCommand() {
        return command;
    }
}