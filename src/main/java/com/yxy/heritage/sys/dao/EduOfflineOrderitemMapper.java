package com.yxy.heritage.sys.dao;
import com.yxy.heritage.sys.bean.EduOfflineOrderitem;
import com.yxy.heritage.sys.bean.EduOfflineOrderitemExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface EduOfflineOrderitemMapper {
    long countByExample(EduOfflineOrderitemExample example);

    int deleteByExample(EduOfflineOrderitemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduOfflineOrderitem record);

    int insertSelective(EduOfflineOrderitem record);

    List<EduOfflineOrderitem> selectByExample(EduOfflineOrderitemExample example);

    EduOfflineOrderitem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduOfflineOrderitem record, @Param("example") EduOfflineOrderitemExample example);

    int updateByExample(@Param("record") EduOfflineOrderitem record, @Param("example") EduOfflineOrderitemExample example);

    int updateByPrimaryKeySelective(EduOfflineOrderitem record);

    int updateByPrimaryKey(EduOfflineOrderitem record);
}