package com.yxy.heritage.sys.dao;
import com.yxy.heritage.sys.bean.EduBooksInfo;
import com.yxy.heritage.sys.bean.EduBooksInfoExample;
import com.yxy.heritage.sys.vo.EduBooksInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EduBooksInfoMapper {
    long countByExample(EduBooksInfoExample example);

    int deleteByExample(EduBooksInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduBooksInfo record);

    int insertSelective(EduBooksInfo record);

    List<EduBooksInfo> selectByExample(EduBooksInfoExample example);

    EduBooksInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduBooksInfo record, @Param("example") EduBooksInfoExample example);

    int updateByExample(@Param("record") EduBooksInfo record, @Param("example") EduBooksInfoExample example);

    int updateByPrimaryKeySelective(EduBooksInfo record);

    int updateByPrimaryKey(EduBooksInfo record);

    //查询详情
    EduBooksInfoVo selectBookById(Integer id);

    //分页查询教材详情信息
    List<EduBooksInfo> queryForPage(Map<String, Object> map);

    //根据id查询名字
    String queryBookNameById(Map<String, Object> map);
}