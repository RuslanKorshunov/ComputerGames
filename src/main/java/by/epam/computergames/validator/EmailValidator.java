package by.epam.computergames.validator;

public class EmailValidator {
    private static final String EMAIL_REGEX = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public static boolean validate(String email) {
        return email.matches(EMAIL_REGEX);
    }
}