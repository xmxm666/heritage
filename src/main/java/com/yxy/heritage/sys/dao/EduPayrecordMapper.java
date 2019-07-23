package com.yxy.heritage.sys.dao;
import com.yxy.heritage.sys.bean.EduPayrecord;
import com.yxy.heritage.sys.bean.EduPayrecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduPayrecordMapper {
    long countByExample(EduPayrecordExample example);

    int deleteByExample(EduPayrecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduPayrecord record);

    int insertSelective(EduPayrecord record);

    List<EduPayrecord> selectByExample(EduPayrecordExample example);

    EduPayrecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduPayrecord record, @Param("example") EduPayrecordExample example);

    int updateByExample(@Param("record") EduPayrecord record, @Param("example") EduPayrecordExample example);

    int updateByPrimaryKeySelective(EduPayrecord record);

    int updateByPrimaryKey(EduPayrecord record);

    //查询线上的报名门数
    Integer queryCourseNum(Integer userId);
}