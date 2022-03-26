package tz.co.mkwanja.africa.payload.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
@Getter
@Setter
public class PageableRequestBody {
    Integer page = 0;
    Integer size = 100;
}
