package com.yxy.heritage.sys.service;

import com.yxy.heritage.sys.bean.EduOrderSettlement;
import com.yxy.heritage.sys.vo.EduOrderSettlementVo;
import com.yxy.heritage.sys.vo.PageBean;

/**
 * @author vvtxw
 * @create 2019-06-15 10:25
 */
public interface EduOrderSettlementService {
    //查询出结算状态的总数据
    PageBean showsSettlement(Integer page, Integer size, Integer schoolId, Integer settlementStatus);

    //更改已经结算状态
    Integer updateAlreadySettlementStatus(EduOrderSettlement eduOrderSettlement);

    //批量修改结算状态
    Integer batchUpdateAlreadySettlementStatus(EduOrderSettlementVo eduOrderSettlementVo);
}
