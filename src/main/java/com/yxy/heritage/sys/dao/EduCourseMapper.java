package com.yxy.heritage.sys.dao;

import com.yxy.heritage.sys.bean.EduClass;
import com.yxy.heritage.sys.bean.EduCourse;
import com.yxy.heritage.sys.vo.EduCourseVo;
import com.yxy.heritage.sys.vo.PageCourseVo;
import com.yxy.heritage.sys.vo.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 课程数据层
 *
 * @author vvtxw
 * @create 2019-04-13 11:34
 */
@Mapper
public interface EduCourseMapper {

    int updateByPrimaryKeySelective(EduCourse record);

    /**
     * 按学校的id来查找专业类别
     *
     * @param eduCourse
     * @return
     */
    public List<EduCourse> listCourseCategoryByShoolId(EduCourse eduCourse);

    /**
     * 根据课程id查询课程信息报名--课程地点老师等
     *
     * @param courseId
     * @return
     */
    public EduCourseVo queryCourseByCourseId(int courseId);

    /**
     * 查询学校报名的总人数
     *
     * @param courseId
     * @return
     */
    int queryAcceptNum(Integer courseId);

    /**
     * 查询课程表相关信息
     *
     * @param week
     * @param schoolId
     * @return
     */
    List<EduCourseVo> queryAllTimetable(@Param("week") String week, @Param("schoolId") String schoolId);

    /**
     * 根据学校id查询课程id
     *
     * @param schoolId
     * @return
     */
    List<Integer> selectCourseIdBySchoolId(Integer schoolId);

    //查询课程相关信息
    EduCourseVo queryCourseInfo(Integer courseId);

    //添加课程
    public void insertCourse(EduCourse course);

    //修改课程信息
    public void updateCourseByCourseId(EduCourse course);

    //根据学校id查找课程列表
    public List<EduCourse> selectCourseListBySchoolId(PageCourseVo pageCourseVo);

    //根据课程id查找课程
    public EduCourseVo selectByCourseId(@Param("courseId") Integer courseId);

    //根据课程id查找课程详细信息
    public List<EduClass> selectClassByCourseId(@Param("courseId") Integer courseId);

    //根据课程id删除课程详细信息
    public void deleteClassByCourseId(@Param("courseId") Integer courseId);

    //根据课程id删除课程
    public void deleteCourseById(@Param("courseId") Integer courseId);

    public List<EduCourse> shelvesCourse(Date date);
}
