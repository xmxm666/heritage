package com.yxy.heritage.sys.dao;

import com.yxy.heritage.sys.bean.EduBooksExtendcategory;
import com.yxy.heritage.sys.bean.EduBooksExtendcategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduBooksExtendcategoryMapper {
    long countByExample(EduBooksExtendcategoryExample example);

    int deleteByExample(EduBooksExtendcategoryExample example);

    int deleteByPrimaryKey(Short id);

    int insert(EduBooksExtendcategory record);

    int insertSelective(EduBooksExtendcategory record);

    List<EduBooksExtendcategory> selectByExample(EduBooksExtendcategoryExample example);

    EduBooksExtendcategory selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") EduBooksExtendcategory record, @Param("example") EduBooksExtendcategoryExample example);

    int updateByExample(@Param("record") EduBooksExtendcategory record, @Param("example") EduBooksExtendcategoryExample example);

    int updateByPrimaryKeySelective(EduBooksExtendcategory record);

    int updateByPrimaryKey(EduBooksExtendcategory record);

    //查询所有的教材分类
    List<EduBooksExtendcategory> queryAllExtendcategory();

}