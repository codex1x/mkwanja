package tz.co.mkwanja.africa.exceptions;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @Value("${custom.error.showStackTrace}")
    Boolean showStackTrace;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceException(
            ResourceNotFoundException ex, WebRequest request) {

        Map<String, String> error = new HashMap<>();
        String message = ex.getMessage();
        if (message != null && message.contains(":")) {
            String sw = message.split(":")[0];
            String en = message.split(":")[1];
            error.put("sw", sw);
            error.put("en", en);
        } else {
            error.put("sw", "Hakuna taarifa iliyopatikana");
            error.put("en", "Resource not found");
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), error, new Date()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDataException(
            InvalidDataException ex, WebRequest request) {

        Map<String, String> error = new HashMap<>();
        String message = ex.getMessage();
        if (message != null && message.contains(":")) {
            String sw = message.split(":")[0];
            String en = message.split(":")[1];
            error.put("sw", sw);
            error.put("en", en);
        } else {
            error.put("sw", "Kuna tatizo kwenye taarifa zilizopitishwa");
            error.put("en", "Invalid data provided");
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), error, new Date()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatchException(
            PasswordMismatchException ex, WebRequest request) {

        Map<String, String> error = new HashMap<>();
        error.put("sw", "Nywila ya sasa sio sahihi");
        error.put("en", "Current password is incorrect");

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, error);
        if (showStackTrace) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ErrorResponse> handleFileStorageException(
            FileStorageException ex, WebRequest request) {

        Map<String, String> error = new HashMap<>();
        error.put("sw", "Imeshindikana kutengeneza faili");
        error.put("en", "Failed to create/store file/directory in the specified path");

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, error);
        if (showStackTrace) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MyFileNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMyFileNotFoundException(
            MyFileNotFoundException ex, WebRequest request) {

        Map<String, String> error = new HashMap<>();
        error.put("sw", "Imeshindikana kupata faili:" + ex.getMessage());
        error.put("en", "File not found:" + ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, error);
        if (showStackTrace) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoAdminException.class)
    public ResponseEntity<ErrorResponse> handleResourceException(
            NoAdminException ex, WebRequest request) {

        Map<String, String> error = new HashMap<>();
        error.put("sw", "Hakuna taarifa iliyopatikana");
        error.put("en", "Resource not found");

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, error);
        if (showStackTrace) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) throws IOException {

        Map<String, String> error = new HashMap<>();
        String message = ex.getMessage();
        if (message != null && message.contains(":")) {
            String sw = message.split(":")[0];
            String en = message.split(":")[1];
            error.put("sw", sw);
            error.put("en", en);
        } else {
            error.put("sw", "Huna ruhusa kupata taarifa hii");
            error.put("en", "Access to resource is restricted.");
        }
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, error);
        if (showStackTrace) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

}