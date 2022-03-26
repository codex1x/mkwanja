package tz.co.mkwanja.africa.payload.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
@Getter
@Setter
public class PageResponse {

    private Long totalItems;
    private Integer totalPages;

    private Object content;

    public PageResponse(Page page) {
        this.totalItems = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }
}
