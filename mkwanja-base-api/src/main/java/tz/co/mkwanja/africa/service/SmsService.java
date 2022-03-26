package tz.co.mkwanja.africa.service;

import tz.co.mkwanja.africa.helpers.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SmsService {


    @Autowired
    @Qualifier("SmsGateway")
    private WebClient webClient;


    @Autowired
    Constants constants;


    public String sendSms(String msisdn, String content){

        String body = jsonString(msisdn,content);

        System.out.println(body);
        String response  =  webClient
                .post()
                .uri(
                        uriBuilder -> uriBuilder
                                .path("sms/v1/text/single")
                                //.queryParam("token", token)
                                .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(String.class)
                .retry(3)
                .block();

        System.out.println("Response: \n" + response);
        return response;

    }



    final String jsonString(String msisdn, String content){

        return  "{" +
                //"    \"from\": \"" + "N-SMS" + "\",\n" +
                "    \"from\": \"N-SMS\"," +
                "    \"to\": \"" + msisdn + "\"," +
                "    \"text\": \"" + content + "\"" +
                "}";
    }



}


