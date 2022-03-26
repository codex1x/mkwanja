package tz.co.mkwanja.africa.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Map;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
@Getter
@Setter
public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    private int code;

    private String status;

    private Map<String, String> message;

    public ErrorResponse() {
    }

    public ErrorResponse(HttpStatus httpStatus, Map<String, String> message) {
        this();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
        this.timestamp = new Date();
    }

    public ErrorResponse(int code, Map<String, String> message, Date timestamp) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }
}
