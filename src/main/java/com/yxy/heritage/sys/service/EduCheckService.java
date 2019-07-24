package com.yxy.heritage.sys.service;


import com.yxy.heritage.sys.bean.EduCheck;
import com.yxy.heritage.sys.vo.EduCheckVo;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-04-15 14:39
 */
public interface EduCheckService {
    /**
     * 根据userId打卡
     *
     * @param record
     * @return
     */
    int doSign(EduCheck record);

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
    List<EduCheckVo> queryNowDayRecord();

}
