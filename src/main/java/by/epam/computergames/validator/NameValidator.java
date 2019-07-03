package by.epam.computergames.validator;

public class NameValidator {
    private static final String REGEX = "[A-z]{1}\\w{0,14}";

    public static boolean validate(String name) {
        return name.matches(REGEX);
    }
}