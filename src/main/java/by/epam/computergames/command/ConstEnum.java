package by.epam.computergames.command;

public enum ConstEnum
{
    LOGIN("login"),
    PASSWORD("password"),
    ROLE("role"),
    NAME("name"),
    SURNAME("surname"),
    SEX("sex"),
    EMAIL("email"),
    NEW_NAME_FORM("new_name_form"),
    NEW_SURNAME_FORM("new_surname_form"),
    NEW_PASSWORD_FORM("new_password_form"),
    NEW_SEX_FORM("new_sex_form"),
    NEW_EMAIL_FORM("new_email_form"),
    COMMAND("command"),
    PAGE("page"),
    LIST("list"),
    GAMES("games"),
    ID("id"),
    GAME_NAME("game_name"),
    GENRE("genre"),
    DEVELOPER("developer"),
    PICTURE("picture");

    String value;

    ConstEnum(String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
