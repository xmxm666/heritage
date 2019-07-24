package com.yxy.heritage.sys.service;


import com.yxy.heritage.sys.bean.EduOfflinePayInfo;
import com.yxy.heritage.sys.vo.PageBean;
import com.yxy.heritage.sys.vo.QueryOffLineVos;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-26 21:25
 */
public interface EduOfflinePayInfoService {

    //生存线下支付付款信息
    Integer insertPayInfo(EduOfflinePayInfo eduOfflinePayInfo);

    //分页支付付款信息列表
    PageBean queryAllOffInfo(Integer page, Integer size, Integer schoolId, String schoolName, String telephone, String payStatus);

    //下线支付付款信息列表
    List<QueryOffLineVos> queryAllOff(Integer schoolId, String schoolName, String telephone, String payStatus);

    //修改缴费状态
    Integer updatePayStatus(EduOfflinePayInfo eduOfflinePayInfo);

    //一键修改缴费状态
    Integer bathUpdataPaystatus(EduOfflinePayInfo eduOfflinePayInfo);

    //查询用户的个数
    List<EduOfflinePayInfo> queryAllPayInfo(String phone);
}
