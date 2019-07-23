package com.yxy.heritage.sys.dao;
import com.yxy.heritage.sys.bean.EduBooksCategory;
import com.yxy.heritage.sys.bean.EduBooksCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduBooksCategoryMapper {
    long countByExample(EduBooksCategoryExample example);

    int deleteByExample(EduBooksCategoryExample example);

    int deleteByPrimaryKey(Short id);

    int insert(EduBooksCategory record);

    int insertSelective(EduBooksCategory record);

    List<EduBooksCategory> selectByExample(EduBooksCategoryExample example);

    EduBooksCategory selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") EduBooksCategory record, @Param("example") EduBooksCategoryExample example);

    int updateByExample(@Param("record") EduBooksCategory record, @Param("example") EduBooksCategoryExample example);

    int updateByPrimaryKeySelective(EduBooksCategory record);

    int updateByPrimaryKey(EduBooksCategory record);

    //查询所有的教材分类
    List<EduBooksCategory> queryAllCategory();
}