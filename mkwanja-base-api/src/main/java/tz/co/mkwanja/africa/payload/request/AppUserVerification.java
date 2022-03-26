package tz.co.mkwanja.africa.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
@Getter
@Setter
public class AppUserVerification {
    @NotBlank
    private String phone;

    @NotBlank
    private String otp;

}