package com.yxy.heritage.sys.service;
import com.yxy.heritage.sys.bean.Order;
import com.yxy.heritage.sys.vo.OrderVo;
import com.yxy.heritage.sys.vo.SubmitOrderVos;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-04-12 13:33
 */

public interface OrderService {

    //按支付状态统计总人数
    public int countPayCourseNum(Map<String, Object> map);


    //按学员的类别支付状态统计总人数
    public int countPayCourseEmployerNum(Map<String, Object> map);

    //查询所有的购买记录
    public List<OrderVo> queryAllRecord(Integer userId);

    //根据订单id查询订单详情
    Order getOrderInfo(String orderId);

    //添加教材订单
    void insertCartToOrder(SubmitOrderVos submitOrderVos, HttpServletRequest request);


    //显示总订单
    List<OrderVo> showOrder(Integer userId, Integer orderStatus);

    //取消订单
    int offAndSubmitOrders(Order order);

    //删除订单
    int deleteOrder(Order order);

    //显示相关订单详情页面
    SubmitOrderVos showOrders(OrderVo orderVo);


















    /*  *//**
     * 根据用户id查询已付款的订单
     * @param userId
     * @return
     *//*
    List<OrderVo> queryAllOrder(int userId);*/

    /*  *//**
     * 根据用户id查询开课的具体时间点
     * @param userId
     * @return
     *//*
    List<OrderVo> queryStartDateTimeByCouserId(int userId);
*/
    /*  *//**
     * 查询courseId
     * @param userId
     * @param status
     * @return
     *//*
    public int queryForCourseId(int userId, int status);

    *//**
     *查询订单的已付款的个数
     * @param userId
     * @param status
     * @return
     *//*
    public int queryCourseNumById(int userId, int status);*/
}
