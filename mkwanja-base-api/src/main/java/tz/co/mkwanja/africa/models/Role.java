package tz.co.mkwanja.africa.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tz.co.mkwanja.africa.models.enums.ERole;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
@Document(collection = "roles")
@Getter
@Setter
public class Role {
    @Id
    private String id;

    private ERole name;

    public Role() {

    }
    public Role(ERole name) {
        this.name = name;
    }

}
