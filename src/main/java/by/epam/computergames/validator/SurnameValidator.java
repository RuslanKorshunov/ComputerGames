package by.epam.computergames.validator;

public class SurnameValidator
{
    private static final String REGEX="[A-z]{1}\\w{0,14}";

    public static boolean validate(String surname)
    {
        return surname.matches(REGEX);
    }
}
