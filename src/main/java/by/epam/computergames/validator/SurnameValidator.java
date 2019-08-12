package by.epam.computergames.validator;

public class SurnameValidator {
    private static final String SURNAME_REGEX = "[A-z]{1}\\w{0,14}";

    public static boolean validate(String surname) {
        return surname.matches(SURNAME_REGEX);
    }
}