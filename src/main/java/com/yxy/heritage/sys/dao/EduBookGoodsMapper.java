package com.yxy.heritage.sys.dao;
import com.yxy.heritage.sys.bean.EduBookGoods;
import com.yxy.heritage.sys.bean.EduBookGoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EduBookGoodsMapper {
    long countByExample(EduBookGoodsExample example);

    int deleteByExample(EduBookGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduBookGoods record);

    int insertSelective(EduBookGoods record);

    List<EduBookGoods> selectByExampleWithBLOBs(EduBookGoodsExample example);

    List<EduBookGoods> selectByExample(EduBookGoodsExample example);

    EduBookGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduBookGoods record, @Param("example") EduBookGoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") EduBookGoods record, @Param("example") EduBookGoodsExample example);

    int updateByExample(@Param("record") EduBookGoods record, @Param("example") EduBookGoodsExample example);

    int updateByPrimaryKeySelective(EduBookGoods record);

    int updateByPrimaryKeyWithBLOBs(EduBookGoods record);

    int updateByPrimaryKey(EduBookGoods record);

    //查询所有的默认的学员分类
    List<EduBookGoods> queryAllBooksGoods(Map<String, Object> map);

    //查询所有的默认搜索name
    List<EduBookGoods> queryAllBookesForlike(Map<String, Object> map);

    //分页展示数据
    List<EduBookGoods> queryForPage(Map<String, Object> map);

    //查找学校名称
    List<String> selectNameBySchoolId(Integer schoolId);
}