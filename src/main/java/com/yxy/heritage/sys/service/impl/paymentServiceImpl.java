package com.yxy.heritage.sys.service.impl;

import com.yxy.heritage.sys.bean.PaymentInfo;
import com.yxy.heritage.sys.bean.PaymentInfoExample;
import com.yxy.heritage.sys.dao.PaymentInfoMapper;
import com.yxy.heritage.sys.service.paymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-05 18:28
 */

@Service
public class paymentServiceImpl implements paymentService {
    @Resource
    private PaymentInfoMapper paymentInfoMapper;

    @Override
    public void savePaymentInfo(PaymentInfo paymentInfo) {
        paymentInfoMapper.insertSelective(paymentInfo);
    }

    @Override
    public PaymentInfo getPaymentInfo(PaymentInfo paymentInfoQuery) {
        PaymentInfoExample paymentInfoExample = new PaymentInfoExample();
        paymentInfoExample.createCriteria().andOutTradeNoEqualTo(paymentInfoQuery.getOutTradeNo());
        List<PaymentInfo> paymentInfos = paymentInfoMapper.selectByExample(paymentInfoExample);
        for (PaymentInfo paymentInfo : paymentInfos) {
            return paymentInfo;
        }
        return null;
    }

    @Override
    public void updatePaymentInfoByOutTradeNo(String outTradeNo, PaymentInfo paymentInfo4Upt) {
        PaymentInfoExample paymentInfoExample = new PaymentInfoExample();
        paymentInfoExample.createCriteria().andOutTradeNoEqualTo(outTradeNo);
        paymentInfoMapper.updateByExampleSelective(paymentInfo4Upt, paymentInfoExample);
    }

    @Override
    public void sendPaymentResult2MQ(String orderId) {

    }
}
