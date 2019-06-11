package by.epam.computergames.validator;

public class DeveloperValidator
{
    private static final String REGEX="[0-9]{1,4}";

    public static boolean validate(String developer)
    {
        return developer.matches(REGEX);
    }
}
