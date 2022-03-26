package tz.co.mkwanja.africa.exceptions;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException() {
        super();
    }

    public PasswordMismatchException(String message) {
        super(message);
    }
}
