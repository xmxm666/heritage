package com.yxy.heritage.sys.dao;

import com.yxy.heritage.sys.bean.PaymentInfo;
import com.yxy.heritage.sys.bean.PaymentInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentInfoMapper {
    long countByExample(PaymentInfoExample example);

    int deleteByExample(PaymentInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PaymentInfo record);

    int insertSelective(PaymentInfo record);

    List<PaymentInfo> selectByExample(PaymentInfoExample example);

    PaymentInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PaymentInfo record, @Param("example") PaymentInfoExample example);

    int updateByExample(@Param("record") PaymentInfo record, @Param("example") PaymentInfoExample example);

    int updateByPrimaryKeySelective(PaymentInfo record);

    int updateByPrimaryKey(PaymentInfo record);
}