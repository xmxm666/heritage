package com.yxy.heritage.sys.service;

import com.yxy.heritage.http.result.WebResult;

import java.util.Map;

public interface SmsService {
    public WebResult sendSms(Map<String, String> phone);
}
