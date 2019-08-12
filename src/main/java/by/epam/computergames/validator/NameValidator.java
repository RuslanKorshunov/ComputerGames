package by.epam.computergames.validator;

public class NameValidator {
    private static final String NAME_REGEX = "[A-z]{1}\\w{0,14}";

    public static boolean validate(String name) {
        return name.matches(NAME_REGEX);
    }
}