package tz.co.mkwanja.africa.exceptions;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
public class NoAdminException extends RuntimeException {
    public NoAdminException() {
        super();
    }

    public NoAdminException(String message) {
        super(message);
    }
}
