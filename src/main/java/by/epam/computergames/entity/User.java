package by.epam.computergames.entity;

public class User
{
    private String login="";
    private String password="";
    private UserEnum type=UserEnum.GUEST;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEnum getType() {
        return type;
    }

    public void setType(UserEnum type) {
        this.type = type;
    }
}
