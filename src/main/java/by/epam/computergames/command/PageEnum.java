package by.epam.computergames.command;

public enum PageEnum
{
    INDEX("index.jsp"),
    LOGIN_PAGE("jsp/login_page.jsp"),
    MAIN_PAGE("jsp/main_page.jsp"),
    USER_PAGE("jsp/user_page.jsp");

    private String path;

    PageEnum(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        return path;
    }
}
