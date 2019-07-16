package com.yxy.heritage.sys.dao;
import com.yxy.heritage.sys.vo.EduArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorksShowMapper {
 public List<EduArticleVo> findUserShowAll(@Param("id") Integer id, @Param("schoolId") Integer schoolId, @Param("type") String type, @Param("search") String search);
 public List<EduArticleVo> findIndexShowAll(@Param("id") Integer id, @Param("schoolId") Integer schoolId, @Param("type") String type, @Param("search") String search);
}
