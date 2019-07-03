package by.epam.computergames.validator;

public class NumberValidator {
    private static final String REGEX = "\\d+";

    public static boolean validate(String developer) {
        return developer.matches(REGEX);
    }
}