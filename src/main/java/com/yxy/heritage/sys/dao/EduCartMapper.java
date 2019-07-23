package com.yxy.heritage.sys.dao;

import com.yxy.heritage.sys.bean.EduCart;
import com.yxy.heritage.sys.bean.EduCartExample;
import com.yxy.heritage.sys.vo.EduCartVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EduCartMapper {
    long countByExample(EduCartExample example);

    int deleteByExample(EduCartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduCart record);

    int insertSelective(EduCart record);

    List<EduCart> selectByExample(EduCartExample example);

    EduCart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduCart record, @Param("example") EduCartExample example);

    int updateByExample(@Param("record") EduCart record, @Param("example") EduCartExample example);

    int updateByPrimaryKeySelective(EduCart record);

    int updateByPrimaryKey(EduCart record);

    /**
     * 展示购物车
     *
     * @param userId
     * @return
     */
    List<Object> selectCartByUserId(Integer userId);

    //统计该商品的数量
    Integer countNum(Map<String, Object> map);

    //查询出订单的购物车信息
    EduCartVo queryOrderInfoFromCart(Map<String, Object> map);
}