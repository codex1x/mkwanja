package tz.co.mkwanja.africa.exceptions;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}
