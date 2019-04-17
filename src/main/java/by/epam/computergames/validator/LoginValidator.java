package by.epam.computergames.validator;

public class LoginValidator
{
    private static final String REGEX="^([A-z]|[0-9]){8,20}$";

    public static boolean validate(String login)
    {
        return login.matches(REGEX);
    }
}
