package tz.co.mkwanja.africa.exceptions;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}