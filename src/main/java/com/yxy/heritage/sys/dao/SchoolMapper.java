package com.yxy.heritage.sys.dao;

import com.yxy.heritage.sys.bean.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SchoolMapper {
    //查找对应学校的所有轮播图
    public School findSlideshowAll(Integer schoolId);
    //添加学校
    public void createSchool(School school);

    //修改学校
    public void updateSchool(School school);

    /**
     * 根据学校id查询坐标
     * @param schoolId
     * @return
     */
    public School queryRecordBySchoolId(Integer schoolId);

    //更换学校查询所有
    public List<School> selectSchoolAll();

    public List<School> selectSchoolList(@Param("schoolId") Integer schoolId, @Param("search") String search);

    public School selectById(@Param("schoolId") Integer schoolId);

    /**
     * 是否启用学校
     * @param schoolId
     * @param isUsing  0-停用， 1-启用
     */
    public void  isUsingSchool(@Param("schoolId") Integer schoolId, @Param("isUsing") Integer isUsing);

    public void deleteSchool(@Param("schoolId") Integer schoolId);

    //查询所有学校（返回学校名和学校Id）
    public List<School> findSchoolAll();
    //根据学校id查找对应学校
    public String findSchoolById(Integer schoolId);
}
