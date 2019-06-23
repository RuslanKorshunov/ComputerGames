package by.epam.computergames.validator;

public class LoginValidator
{
    private static final String REGEX="^\\w{8,20}$";

    public static boolean validate(String login)
    {
        return login.matches(REGEX);
    }
}
