package com.yxy.heritage.sys.web;

import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 发送短信
 */

@RestController
@RequestMapping("/her/sms")
public class SmsControll {

    @Autowired
    public SmsService smsService;

    /**
     * 发送短信
     * @param phone
     * @return
     */
    @PostMapping(value = "/sendSms")
    public WebResult sendSms(@RequestBody Map<String, String> phone) {
        return smsService.sendSms(phone);
    }

}
