package com.yxy.heritage.sys.dao;
import com.yxy.heritage.sys.bean.EduOfflinePayInfo;
import com.yxy.heritage.sys.bean.EduOfflinePayInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EduOfflinePayInfoMapper {
    long countByExample(EduOfflinePayInfoExample example);

    int deleteByExample(EduOfflinePayInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduOfflinePayInfo record);

    int insertSelective(EduOfflinePayInfo record);

    List<EduOfflinePayInfo> selectByExample(EduOfflinePayInfoExample example);

    EduOfflinePayInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduOfflinePayInfo record, @Param("example") EduOfflinePayInfoExample example);

    int updateByExample(@Param("record") EduOfflinePayInfo record, @Param("example") EduOfflinePayInfoExample example);

    int updateByPrimaryKeySelective(EduOfflinePayInfo record);

    int updateByPrimaryKey(EduOfflinePayInfo record);


    //线下学员展示
    List<EduOfflinePayInfo> selectOfflineList(Map<String, Object> map);

    //根据手机号码显示最新的线下支付
    EduOfflinePayInfo queryShowOfflinePayInfo(String telephone);
}