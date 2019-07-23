package com.yxy.heritage.sys.service;

import com.yxy.heritage.sys.bean.EduCart;
import com.yxy.heritage.sys.vo.EduCartVo;
import com.yxy.heritage.sys.vo.PageBean;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-17 12:31
 */
public interface EduCartService {
    //添加购物车
    void addCart(Integer goodsId, Integer goodsNum, Integer userId, Integer schoolId);

    //展示购物车
    PageBean showAllCartByUserId(Integer userId);

    //统计购物车的数量
    long countCartNum(Integer userId);

    //统计该商品的数量
    Integer countNum(Integer userId, Integer goodsId);

    //修改购物车数量
    Integer updateCart(EduCart cart);

    //查询出订单的购物车信息
    EduCartVo queryOrderInfoFromCart(Integer userId, Integer goodsId);

    //查询购物车是否有此教材
    List<EduCart> queryCartByUserIdAndGoodSId(Integer userId, Integer goodsId);
}
