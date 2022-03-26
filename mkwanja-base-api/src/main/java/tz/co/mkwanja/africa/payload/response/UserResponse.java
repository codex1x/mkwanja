package tz.co.mkwanja.africa.payload.response;

import tz.co.mkwanja.africa.models.Role;
import tz.co.mkwanja.africa.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */

@Getter
@Setter
public class UserResponse {

    private String id;

    private String firstname;


    private String lastname;


    private String phone;

    private String email;

    private HashMap<String, String> userId;

    private String profile;

    private String nextStep;

    private Set<Role> roles = new HashSet<>();

    private String status;

    public UserResponse() {
    }

    public UserResponse(String id,String firstname, String lastname, String phone, String email,Set<Role> roles,String profile,HashMap<String, String> userId, String status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.userId = userId;
        this.roles = roles;
        this.profile = profile;
        this.id = id;
        this.status = status;

    }

    public UserResponse(User user){
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.userId = user.getUserId();
        this.roles = user.getRoles();
        this.profile = user.getProfile();
        this.id = user.getId();
        this.status = user.getStatus();
    }

}
