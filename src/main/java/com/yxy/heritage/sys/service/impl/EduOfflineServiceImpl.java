package com.yxy.heritage.sys.service.impl;

import com.yxy.heritage.sys.bean.EduOfflineOrder;
import com.yxy.heritage.sys.bean.EduOfflineOrderitem;
import com.yxy.heritage.sys.service.EduOfflineOrderService;
import com.yxy.heritage.sys.service.EduOfflineOrderitemService;
import com.yxy.heritage.sys.service.EduOfflineService;
import com.yxy.heritage.sys.vo.QueryOffLineVos;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-24 15:38
 */
@Service
public class EduOfflineServiceImpl implements EduOfflineService {
    @Resource
    private EduOfflineOrderService eduOfflineOrderService;

    @Resource
    private EduOfflineOrderitemService eduOfflineOrderitemService;


    @Override
    public QueryOffLineVos queryAllOffInfo(String schoolId, Integer userId) {
//        Page<Object> pageAll = PageHelper.startPage(page, size);废气
        List<EduOfflineOrder> eduOfflineOrders = eduOfflineOrderService.queryAllOffOrder(schoolId, userId);
        List<EduOfflineOrderitem> eduOfflineOrderitems = eduOfflineOrderitemService.queryAllOffOrderItem(schoolId, userId);

        BigDecimal accountAllMoney = new BigDecimal(0);

        QueryOffLineVos queryOffLineVos = new QueryOffLineVos();
        for (EduOfflineOrder eduOfflineOrder : eduOfflineOrders) {
            queryOffLineVos.setSchoolName(eduOfflineOrder.getSchoolName());
            queryOffLineVos.setUserName(eduOfflineOrder.getUserName());
            queryOffLineVos.setPhone(eduOfflineOrder.getPhone());
//            queryOffLineVos.setOrderitems(eduOfflineOrderitems);
            accountAllMoney = accountAllMoney.add(eduOfflineOrder.getCousePrice());
            queryOffLineVos.setAccountAllMoney(accountAllMoney);
        }
//        long total = pageAll.getTotal();
        return queryOffLineVos;
    }
}
