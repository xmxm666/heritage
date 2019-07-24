package com.yxy.heritage.sys.dao;

import com.yxy.heritage.sys.bean.EduSettlementOrderDetails;
import com.yxy.heritage.sys.bean.EduSettlementOrderDetailsExample;
import com.yxy.heritage.sys.vo.EduSettlementOrderDetailsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduSettlementOrderDetailsMapper {
    long countByExample(EduSettlementOrderDetailsExample example);

    int deleteByExample(EduSettlementOrderDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduSettlementOrderDetails record);

    int insertSelective(EduSettlementOrderDetails record);

    List<EduSettlementOrderDetails> selectByExample(EduSettlementOrderDetailsExample example);

    EduSettlementOrderDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduSettlementOrderDetails record, @Param("example") EduSettlementOrderDetailsExample example);

    int updateByExample(@Param("record") EduSettlementOrderDetails record, @Param("example") EduSettlementOrderDetailsExample example);

    int updateByPrimaryKeySelective(EduSettlementOrderDetails record);

    int updateByPrimaryKey(EduSettlementOrderDetails record);

    //查询订单明细列表
    List<EduSettlementOrderDetailsVo> showSettlementDetail(EduSettlementOrderDetailsVo eduSettlementOrderDetailsVo);
}