package tz.co.mkwanja.africa.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
@Getter
@Setter
public class OwnerInformationRequest {
    //@NotBlank
    @Size(min = 3, max = 20)
    private String firstname;

    //@NotBlank
    @Size(min = 3, max = 20)
    private String lastname;

    //@Opt
    //@Size(max = 12)
    //private String phone;

    //@NotBlank
    @Size(max = 50)
    @Email
    private String email;




}
