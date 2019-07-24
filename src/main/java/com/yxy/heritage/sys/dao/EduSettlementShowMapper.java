package com.yxy.heritage.sys.dao;

import com.yxy.heritage.sys.bean.EduSettlementShow;
import com.yxy.heritage.sys.bean.EduSettlementShowExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduSettlementShowMapper {
    long countByExample(EduSettlementShowExample example);

    int deleteByExample(EduSettlementShowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduSettlementShow record);

    int insertSelective(EduSettlementShow record);

    List<EduSettlementShow> selectByExample(EduSettlementShowExample example);

    EduSettlementShow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduSettlementShow record, @Param("example") EduSettlementShowExample example);

    int updateByExample(@Param("record") EduSettlementShow record, @Param("example") EduSettlementShowExample example);

    int updateByPrimaryKeySelective(EduSettlementShow record);

    int updateByPrimaryKey(EduSettlementShow record);
}