package com.i.and.you.global.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class Pagination extends ApiResult {
    private boolean isLast;
    private long totalRecords;

    public Pagination(boolean isLast, long totalRecords) {
        this.isLast = isLast;
        this.totalRecords = totalRecords;
    }

    public static Pagination from(Page<?> page) {
        return new Pagination(page.isLast(), page.getTotalElements());
    }
}
