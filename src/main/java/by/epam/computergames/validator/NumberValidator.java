package by.epam.computergames.validator;

public class NumberValidator {
    private static final String NUMBER_REGEX = "\\d+";

    public static boolean validate(String developer) {
        return developer.matches(NUMBER_REGEX);
    }
}