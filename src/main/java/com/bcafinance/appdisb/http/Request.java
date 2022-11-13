package com.bcafinance.appdisb.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Request {
    private Integer page = 0;
    private Integer size = 10;
}
