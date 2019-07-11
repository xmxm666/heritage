package com.yxy.heritage.config;


import com.yxy.heritage.http.result.WebResult;

public class MessageCenterException extends RuntimeException {
 
    public MessageCenterException(WebResult apiResponse, Exception exception){
        this.apiResponse = apiResponse;
        this.exception = exception;
    }
 
    private Exception exception;
    private WebResult apiResponse;
 
    public Exception getException() {
        return exception;
    }
 
    public void setException(Exception exception) {
        this.exception = exception;
    }
 
    public WebResult getApiResponse() {
        return apiResponse;
    }
 
    public void setApiResponse(WebResult apiResponse) {
        this.apiResponse = apiResponse;
    }
}