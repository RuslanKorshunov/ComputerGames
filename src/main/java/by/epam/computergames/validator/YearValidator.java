package by.epam.computergames.validator;

public class YearValidator
{
    private static final String REGEX="\\d{1,4}";

    public static boolean validate(String year)
    {
        return year.matches(REGEX);
    }
}
