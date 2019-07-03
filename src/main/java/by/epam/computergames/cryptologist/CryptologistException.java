package by.epam.computergames.cryptologist;

public class CryptologistException extends Exception {
    public CryptologistException() {
    }

    public CryptologistException(String message) {
        super(message);
    }

    public CryptologistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptologistException(Throwable cause) {
        super(cause);
    }
}