package com.yxy.heritage.sys.dao;
import com.yxy.heritage.sys.bean.EduOfflineNum;
import com.yxy.heritage.sys.bean.EduOfflineNumExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduOfflineNumMapper {
    long countByExample(EduOfflineNumExample example);

    int deleteByExample(EduOfflineNumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduOfflineNum record);

    int insertSelective(EduOfflineNum record);

    List<EduOfflineNum> selectByExample(EduOfflineNumExample example);

    EduOfflineNum selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduOfflineNum record, @Param("example") EduOfflineNumExample example);

    int updateByExample(@Param("record") EduOfflineNum record, @Param("example") EduOfflineNumExample example);

    int updateByPrimaryKeySelective(EduOfflineNum record);

    int updateByPrimaryKey(EduOfflineNum record);
}