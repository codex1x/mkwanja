package tz.co.mkwanja.africa.config;


import tz.co.mkwanja.africa.helpers.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GeneralWebClient {

    @Autowired
    Constants constants;

    @Bean("SmsGateway")
    public WebClient smsGatewayClient() {

        String url = "" + constants.smsGatewayUrl;
        String userName = "" + constants.smsGatewayUsername;
        String password = "" + constants.smsGatewayPassword;

        System.out.println(userName + " " + password);

        return WebClient.builder()
                .baseUrl(url)
                .defaultHeaders(header ->
                        header.setBasicAuth(userName, password)
                )
                .build();
    }

    @Bean("caspit")
    public WebClient caspitWebClient() {

        return WebClient.builder()
                .baseUrl("https://app.caspitweb.biz/api/v1")
                .build();
    }
}
