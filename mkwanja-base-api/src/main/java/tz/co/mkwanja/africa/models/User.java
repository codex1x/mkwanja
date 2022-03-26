package tz.co.mkwanja.africa.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
@Document(collection = "users")
@Getter
@Setter
public class User {
    @Id
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    @NotBlank
    @Size(max = 20)
    private String firstname;

    @NotBlank
    @Size(max = 20)
    private String lastname;

    @NotBlank
    @Size(max = 12)
    private String phone;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    private HashMap<String, String> userId;

    @Size(max = 100)
    private String profile;


    @DBRef
    private Set<Role> roles = new HashSet<>();

    @NotBlank
    private String status;

    public User() {
    }

    public User(String firstname, String lastname, String phone, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.createdDate = LocalDate.now();
    }

    public String getName() {
        return new StringBuilder()
                .append(firstname)
                .append(" ")
                .append(lastname)
                .toString();
    }


}
