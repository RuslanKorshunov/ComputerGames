package by.epam.computergames.validator;

public class GenreValidator
{
    private static final String REGEX="[0-9]{1,4}";

    public static boolean validate(String genre)
    {
        return genre.matches(REGEX);
    }
}
