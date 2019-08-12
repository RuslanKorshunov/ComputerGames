package by.epam.computergames.validator;

public class PasswordValidator {
    private static final String PASSWORD_REGEX = "^\\w{4,40}$";

    public static boolean validate(String password) {
        return password.matches(PASSWORD_REGEX);
    }
}