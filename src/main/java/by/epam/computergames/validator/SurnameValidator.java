package by.epam.computergames.validator;

public class SurnameValidator
{
    private static final String REGEX="[A-z]{1}([A-z]|[0-9]){0,14}";

    public static boolean validate(String name)
    {
        return name.matches(REGEX);
    }
}
