package com.bcafinance.appdisb.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Meta {
    private Integer currentPage;
    private Integer totalPage;
    private Integer size;
    private Long totalData;
}
