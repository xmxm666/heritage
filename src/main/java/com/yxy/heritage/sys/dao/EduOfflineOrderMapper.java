package com.yxy.heritage.sys.dao;

import com.yxy.heritage.sys.bean.EduOfflineOrder;
import com.yxy.heritage.sys.bean.EduOfflineOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduOfflineOrderMapper {
    long countByExample(EduOfflineOrderExample example);

    int deleteByExample(EduOfflineOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduOfflineOrder record);

    int insertSelective(EduOfflineOrder record);

    List<EduOfflineOrder> selectByExample(EduOfflineOrderExample example);

    EduOfflineOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduOfflineOrder record, @Param("example") EduOfflineOrderExample example);

    int updateByExample(@Param("record") EduOfflineOrder record, @Param("example") EduOfflineOrderExample example);

    int updateByPrimaryKeySelective(EduOfflineOrder record);

    int updateByPrimaryKey(EduOfflineOrder record);

    //查询线下支付记录
    List<EduOfflineOrder> queryAllOffRecord(Integer userId);

    //线下支付的人数
    Integer queryOffRecordNum(Integer courseId);

    //统计报名课程的门数
    Integer queryCountCourseNum(Integer userId);
}