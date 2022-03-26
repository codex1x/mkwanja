package tz.co.mkwanja.africa.exceptions;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
public class MyFileNotFoundException extends RuntimeException {
    public MyFileNotFoundException() {
        super();
    }

    public MyFileNotFoundException(String message) {
        super(message);
    }

}
