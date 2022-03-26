package tz.co.mkwanja.africa.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Map;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
@Document(collection = "notifications")
@Getter
@Setter
public class Notification {
    @Id
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @NotBlank
    private String title;

    @NotBlank
    private String body;

    @NotBlank
    private String page;

    private Map<String,Object> data;

    @NotBlank
    private String userId;

    private String status;

    public Notification() {
    }


}
