package tz.co.mkwanja.africa.exceptions;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
public class InvalidDataException extends RuntimeException{

    public InvalidDataException(String message) {
        super(message);
    }
}