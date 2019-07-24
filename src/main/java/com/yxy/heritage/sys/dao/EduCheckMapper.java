package com.yxy.heritage.sys.dao;

import com.yxy.heritage.sys.bean.EduCheck;
import com.yxy.heritage.sys.bean.EduCheckExample;
import com.yxy.heritage.sys.vo.EduCheckVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduCheckMapper {
    long countByExample(EduCheckExample example);

    int deleteByExample(EduCheckExample example);

    int deleteByPrimaryKey(Integer checkId);

    int insert(EduCheck record);

    /**
     * 根据userId打卡
     *
     * @param record
     * @return
     */
    int insertSelective(EduCheck record);

    List<EduCheck> selectByExample(EduCheckExample example);

    EduCheck selectByPrimaryKey(Integer checkId);

    int updateByExampleSelective(@Param("record") EduCheck record, @Param("example") EduCheckExample example);

    int updateByExample(@Param("record") EduCheck record, @Param("example") EduCheckExample example);

    int updateByPrimaryKeySelective(EduCheck record);

    int updateByPrimaryKey(EduCheck record);

    /**
     * 根据userId查询打卡记录
     *
     * @param userId
     * @return
     */
    List<EduCheckVo> queryAllPunchRecord(Integer userId);

    /**
     * 查询当天的打开课程信息
     *
     * @return
     */
    List<EduCheckVo> queryNowDayRecord(String nowDay);


}