package com.yxy.heritage.sys.service;

import com.yxy.heritage.sys.vo.QueryOffLineVos;

/**
 * @author vvtxw
 * @create 2019-05-24 15:36
 */
public interface EduOfflineService {


    //查询线下报名信息详情--
    QueryOffLineVos queryAllOffInfo(String schoolId, Integer userId);


}
