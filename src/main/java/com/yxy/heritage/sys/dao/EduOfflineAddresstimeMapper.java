package com.yxy.heritage.sys.dao;

import com.yxy.heritage.sys.bean.EduOfflineAddresstime;
import com.yxy.heritage.sys.bean.EduOfflineAddresstimeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduOfflineAddresstimeMapper {
    long countByExample(EduOfflineAddresstimeExample example);

    int deleteByExample(EduOfflineAddresstimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduOfflineAddresstime record);

    int insertSelective(EduOfflineAddresstime record);

    List<EduOfflineAddresstime> selectByExample(EduOfflineAddresstimeExample example);

    EduOfflineAddresstime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduOfflineAddresstime record, @Param("example") EduOfflineAddresstimeExample example);

    int updateByExample(@Param("record") EduOfflineAddresstime record, @Param("example") EduOfflineAddresstimeExample example);

    int updateByPrimaryKeySelective(EduOfflineAddresstime record);

    int updateByPrimaryKey(EduOfflineAddresstime record);

    //查找学校名称
    List<String> selectNameBySchoolId(Integer schoolId);


}