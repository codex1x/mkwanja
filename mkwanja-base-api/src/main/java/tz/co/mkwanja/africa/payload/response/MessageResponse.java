package tz.co.mkwanja.africa.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
@Getter
@Setter
public class MessageResponse {
    private int code;
    private Map<String, String> message;
    private Object data;

    public MessageResponse(Integer code, Map<String, String> message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public MessageResponse(Integer code, String eng, String sw, Object data) {
        Map<String, String> message = new HashMap<>();
        message.put("en", eng);
        message.put("sw", sw);
        this.message = message;
        this.code = code;
        this.data = data;
    }

}