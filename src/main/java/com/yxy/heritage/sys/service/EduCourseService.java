package com.yxy.heritage.sys.service;


import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.EduCourse;
import com.yxy.heritage.sys.vo.EduCourseVo;
import com.yxy.heritage.sys.vo.PageCourseVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-04-13 11:37
 */
public interface EduCourseService {
    /**
     * 按学校的id来查找专业类别
     *
     * @param eduCourse
     * @return
     */
    public List<EduCourse> listCourseCategoryByShoolId(EduCourse eduCourse);

    /**
     * 根据课程id查询课程信息报名
     *
     * @param courseId
     * @return
     */
    public Map<String, Object> queryCourseByCourseId(Integer userId, Integer courseId, HttpServletRequest request);

    EduCourseVo queryCourseByCategory(Integer courseId);


    /**
     * 查询课程表相关信息
     *
     * @param week
     * @param schoolId
     * @return
     */
    List<EduCourseVo> queryAllTimetable(String week, String schoolId);

    /**
     * 设置所有的时间
     *
     * @param eduCourse
     * @return
     */
    int updateAllTime(EduCourse eduCourse);

    //根据课程id获取结束时间
    EduCourse queryEndTimeByCourseId(Integer courseId);

    public WebResult createCourse(EduCourseVo courseVo);

    public WebResult courseList(PageCourseVo pageCourseVo);

    public WebResult findCourse(Integer courseId);

    public WebResult deleteCourse(String[] courseId);

    //课程 启用/停用 页面
    public WebResult updateIsenable(EduCourseVo course);

    //下架过期课程
    public void shelvesCourse();
}
