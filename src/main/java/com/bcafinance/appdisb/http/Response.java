package com.bcafinance.appdisb.http;

import com.bcafinance.appdisb.constant.ResponseCode;
import com.bcafinance.appdisb.constant.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Response<T> {
    private String errorCode;
    private String errorMessage;
    private T data;
    private Meta meta;

    public void setResponseCode(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public void setSuccess(T data,Meta meta) {
        this.errorCode = ResponseCode.SUCCESS;
        this.errorMessage = ResponseMessage.SUCCESS;
        this.data = data;
        this.meta = meta;
    }

    public void setFailed() {
        this.errorCode = ResponseCode.FAILED;
        this.errorMessage = ResponseMessage.FAILED;
    }
}
