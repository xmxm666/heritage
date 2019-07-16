package com.yxy.heritage.sys.dao;

import com.yxy.heritage.sys.bean.EduClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EduClassMapper {

    int insert(EduClass record);

    /**
     * 根据课程id查询课程信息报名--报名时间
     *
     * @param courseId
     * @return
     */
    public List<EduClass> queryCourseTimeByCourseId(int courseId);
}