package tz.co.mkwanja.africa.payload.response;

import java.util.List;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String nextStep;//owner_information->store_information->completed
    private List<String> roles;

    public JwtResponse(String accessToken, String id, String firstname, String lastname, String username, String email, List<String> roles, String nextStep) {
        this.token = accessToken;
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.nextStep = nextStep;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNextStep() {
        return nextStep;
    }

    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }

    public List<String> getRoles() {
        return roles;
    }
}
