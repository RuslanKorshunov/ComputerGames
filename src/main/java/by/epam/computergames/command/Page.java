package by.epam.computergames.command;

public enum Page
{
    INDEX("index.jsp"),
    LOGIN_PAGE("jsp/login_page.jsp"),
    MAIN_PAGE("jsp/main_page.jsp"),
    USER_PAGE("jsp/user_page.jsp"),
    REGISTRATION_PAGE("jsp/registration_page.jsp"),
    GAME_PAGE("jsp/game_page.jsp"),
    SEARCH_PAGE("jsp/search_page.jsp"),
    REVIEW_PAGE("jsp/review_page.jsp");

    private String path;

    Page(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        return path;
    }
}
