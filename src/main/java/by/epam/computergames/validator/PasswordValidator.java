package by.epam.computergames.validator;

public class PasswordValidator
{
    private static final String REGEX="^\\w{4,40}$";

    public static boolean validate(String password)
    {
        return password.matches(REGEX);
    }
}
