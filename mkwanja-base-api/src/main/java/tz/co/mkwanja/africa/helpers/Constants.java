package tz.co.mkwanja.africa.helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
@Configuration
public class Constants {
    @Value("${services.appUrl}")
    public String appUrl;

    @Value("${services.smsGateway.url}")
    public String smsGatewayUrl;

    @Value("${services.smsGateway.username}")
    public String smsGatewayUsername;

    @Value("${services.smsGateway.password}")
    public String smsGatewayPassword;

}
