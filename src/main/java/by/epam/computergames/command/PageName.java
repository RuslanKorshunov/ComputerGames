package by.epam.computergames.command;

public enum PageName {
    INDEX("index.jsp"),
    AUTHORIZATION_PAGE("jsp/authorization_page.jsp"),
    MAIN_PAGE("jsp/main_page.jsp"),
    USER_PAGE("jsp/user_page.jsp"),
    REGISTRATION_PAGE("jsp/registration_page.jsp"),
    GAME_PAGE("jsp/game_page.jsp"),
    SEARCH_PAGE("jsp/search_page.jsp"),
    REVIEW_PAGE("jsp/review_page.jsp"),
    REVIEWS_PAGE("jsp/reviews_page.jsp"),
    CHANGE_GAME_PAGE("jsp/change_game_page.jsp");

    private String path;

    PageName(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
