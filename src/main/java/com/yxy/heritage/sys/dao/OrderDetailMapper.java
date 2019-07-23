package com.yxy.heritage.sys.dao;
import com.yxy.heritage.sys.bean.OrderDetail;
import com.yxy.heritage.sys.bean.OrderDetailExample;
import com.yxy.heritage.sys.vo.OrderDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDetailMapper {
    long countByExample(OrderDetailExample example);

    int deleteByExample(OrderDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    List<OrderDetail> selectByExample(OrderDetailExample example);

    OrderDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    int updateByExample(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

    //查询所有的订单详情
    List<OrderDetail> selectByOrderId(Integer orderId);

    //查询所有的订单详情
    List<OrderDetailVo> selectById(Integer id);
}