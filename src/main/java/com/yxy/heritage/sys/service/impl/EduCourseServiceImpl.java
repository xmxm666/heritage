package com.yxy.heritage.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxy.heritage.constant.OrderStatus;
import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.EduClass;
import com.yxy.heritage.sys.bean.EduCourse;
import com.yxy.heritage.sys.bean.School;
import com.yxy.heritage.sys.dao.EduClassMapper;
import com.yxy.heritage.sys.dao.EduCourseMapper;
import com.yxy.heritage.sys.dao.SchoolMapper;
import com.yxy.heritage.sys.dao.UserMapper;
import com.yxy.heritage.sys.service.EduCourseService;
import com.yxy.heritage.sys.vo.AdminVo;
import com.yxy.heritage.sys.vo.EduCourseVo;
import com.yxy.heritage.sys.vo.PageCourseVo;
import com.yxy.heritage.sys.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 课程报名业务层
 *
 * @author vvtxw
 * @create 2019-04-13 11:38
 */
@Service
public class EduCourseServiceImpl implements EduCourseService {

    @Resource
    private EduCourseMapper eduCourseMapper;

    @Resource
    private EduClassMapper eduClassMapper;

    @Resource
    private SchoolMapper schoolMapper;

    /**
     * 按学校的id来查找专业类别
     *
     * @param eduCourse
     * @return
     */
    @Override
    public List<EduCourse> listCourseCategoryByShoolId(EduCourse eduCourse) {
        return eduCourseMapper.listCourseCategoryByShoolId(eduCourse);
    }

    @Override
    public EduCourseVo queryCourseByCategory(Integer courseId) {
        //1.根据courseId查询订单的信息
        EduCourseVo courseInfo = eduCourseMapper.queryCourseByCourseId(courseId);
        //2.根据courseId查询开课的具体时间
        List<EduClass> eduClasses = eduClassMapper.queryCourseTimeByCourseId(courseId);
        //3.生成报名详情信息
        courseInfo.setClassList(eduClasses);
        return courseInfo;
    }

    /**
     * 课程表
     *
     * @param week
     * @param schoolId
     * @return
     */
    @Override
    public List<EduCourseVo> queryAllTimetable(String week, String schoolId) {
        return eduCourseMapper.queryAllTimetable(week, schoolId);
    }


    @Override
    public int updateAllTime(EduCourse eduCourse) {
        //先查询出所有学校的课程
        List<EduCourse> eduCourses = eduCourseMapper.listCourseCategoryByShoolId(eduCourse);
        EduCourse updateCourse = new EduCourse();
        updateCourse.setStartDate(eduCourse.getStartDate());
        updateCourse.setEndDate(eduCourse.getEndDate());
        int i = 1;
        for (EduCourse eduCours : eduCourses) {
            updateCourse.setCourseId(eduCours.getCourseId());
            //然后对所有学校课程的时间进行修改
            i = eduCourseMapper.updateByPrimaryKeySelective(updateCourse);
            if (i < 1) {
                return -1;
            }
        }
        return i;
    }

    /**
     * 添加/修改  课程
     *
     * @param courseVo
     * @return
     */
    @Transactional
    @Override
    public WebResult createCourse(EduCourseVo courseVo) {
        EduCourse course = new EduCourse();
        if (StringUtils.isBlank(courseVo.getCourseName())) {
            return new WebResult("400", "课程名称不能为空", "");
        }

        if (courseVo.getCourseTeacher() == null) {
            return new WebResult("400", "请添加课程教师", "");
        }

        if (StringUtils.isBlank(courseVo.getSchoolId())) {
            return new WebResult("400", "请选择学校", "");
        }

        List<EduClass> classList = courseVo.getClassList();
        Date startDate = courseVo.getStartDate();
        Date endDate = courseVo.getEndDate();

        //如果课程启用, 相关字段验证
        if (courseVo.getIsenable().compareTo(1) == 0) {
            WebResult security = security(courseVo, classList);
            if (security != null) {
                return security;
            }
        }
        course.setCourseName(courseVo.getCourseName());
        course.setCourseTeacher(courseVo.getCourseTeacher());
        course.setCoursePrice(courseVo.getCoursePrice());
        course.setPlaceClass(courseVo.getPlaceClass());
        course.setStartDate(startDate);
        course.setEndDate(endDate);
        course.setPeopleNumber(courseVo.getPeopleNumber());
        course.setSchoolId(courseVo.getSchoolId());
        course.setIsenable(courseVo.getIsenable());


        Integer courseId = courseVo.getCourseId();


        String msg = "课程添加成功";
        if (courseId != null) {
            course.setCourseId(courseId);
            eduCourseMapper.updateCourseByCourseId(course);
            //先删除原有的详细信息
            eduCourseMapper.deleteClassByCourseId(course.getCourseId());

            msg = "课程修改成功";
        } else {
            eduCourseMapper.insertCourse(course);
        }

        if (classList != null) {
            for (EduClass eduClass : classList) {
                eduClass.setCourseIds(course.getCourseId());
                eduClassMapper.insert(eduClass);
            }
        }
        //添加成功之后，判断是否开启，

        return new WebResult("200", msg, "");
    }

    /**
     * 课程相关信息验证
     *
     * @param courseVo
     * @return
     */
    public WebResult security(EduCourse courseVo, List<EduClass> classList) {
        Date startDate = courseVo.getStartDate();
        Date endDate = courseVo.getEndDate();
        //检查是否有相同的课程已经启用
        PageCourseVo pageVo = new PageCourseVo();
        pageVo.setSchoolId(Integer.valueOf(courseVo.getSchoolId()));
        pageVo.setIsenable(courseVo.getIsenable());
        pageVo.setSearch(courseVo.getCourseName());
        List<EduCourse> eqName = eduCourseMapper.selectCourseListBySchoolId(pageVo);
        for (EduCourse eduCourse : eqName) {
            if (eduCourse.getCourseName().equals(courseVo.getCourseName())) {
                if (courseVo.getCourseId() == null) {
                    return new WebResult("400", "已有“" + courseVo.getCourseName() + "”,请先停用！", "");
                }
                if (!courseVo.getCourseId().equals(eduCourse.getCourseId())) {
                    return new WebResult("400", "已有“" + courseVo.getCourseName() + "”,请先停用！", "");
                }
            }
        }
        if (classList == null || classList.size() == 0) {
            return new WebResult("400", "请选择‘" + courseVo.getCourseName() + "’的上课时间", "");
        }
        if (courseVo.getCoursePrice() == null || courseVo.getCoursePrice().compareTo(BigDecimal.ZERO) < 0) {
            return new WebResult("400", "课程‘" + courseVo.getCourseName() + "’的价格不正确", "");
        }
        if (StringUtils.isBlank(courseVo.getPlaceClass())) {
            return new WebResult("400", "请添加‘" + courseVo.getCourseName() + "’的上课地点", "");
        }
        if (startDate == null || endDate == null) {
            return new WebResult("400", "请添加课程‘" + courseVo.getCourseName() + "’的日期", "");
        }
        if (startDate.after(endDate)) {
            return new WebResult("400", "‘" + courseVo.getCourseName() + "’的结束日期不能早于开课日期", "");
        }
        if (courseVo.getPeopleNumber() == null || courseVo.getPeopleNumber().compareTo(0) <= 0) {
            return new WebResult("400", "请添加课程‘" + courseVo.getCourseName() + "’的人数", "");
        }
        //判断“HH:mm”
        Pattern p = Pattern.compile("([0-1]?[0-9]|2[0-3]):([0-5][0-9])");
        for (EduClass eduClass : classList) {
            String startTime = eduClass.getStartTime();
            String endTime = eduClass.getEndTime();
            if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
                return new WebResult("400", "请添加课程时间", "");
            }
            if (!p.matcher(startTime).matches() || !p.matcher(endTime).matches()) {
                return new WebResult("400", "请添加课程时间", "");
            }
            if (StringUtils.isBlank(eduClass.getWeek())) {
                return new WebResult("400", "请选择周几上课", "");
            }
        }
        return null;
    }

    /**
     * 课程列表
     *
     * @param pageCourseVo
     * @return
     */
    @Override
    public WebResult courseList(PageCourseVo pageCourseVo) {
        if (pageCourseVo.getPageSize() == 0) {
            List<EduCourse> courseList = eduCourseMapper.selectCourseListBySchoolId(pageCourseVo);
            return new WebResult("200", "查询成功", courseList);
        }
        PageHelper.startPage(pageCourseVo.getPageNum(), pageCourseVo.getPageSize());
        List<EduCourse> courseList = eduCourseMapper.selectCourseListBySchoolId(pageCourseVo);
        PageInfo pageInfo = new PageInfo<>(courseList);
        return new WebResult("200", "查询成功", pageInfo);
    }

    /**
     * 进入修改课程页面
     *
     * @param courseId
     * @return
     */
    @Override
    public WebResult findCourse(Integer courseId) {
        EduCourseVo courseVo = eduCourseMapper.selectByCourseId(courseId);
        if (courseVo == null) {
            return new WebResult("400", "请选择课程", "");
        }
        List<EduClass> classList = eduCourseMapper.selectClassByCourseId(courseVo.getCourseId());
        courseVo.setClassList(classList);
        School school = schoolMapper.selectById(Integer.valueOf(courseVo.getSchoolId()));
        courseVo.setSchoolName(school == null ? "" : school.getSchoolName());
        return new WebResult("200", "查询成功", courseVo);
    }

    /**
     * 删除课程
     *
     * @param courseIds
     * @return
     */
    @Override
    public WebResult deleteCourse(String[] courseIds) {
        if (courseIds == null || courseIds.length == 0) {
            return new WebResult("400", "请选择课程", "");
        }
        for (String s : courseIds) {
            Integer courseId = Integer.valueOf(s);
            eduCourseMapper.deleteClassByCourseId(courseId);
            eduCourseMapper.deleteCourseById(courseId);
        }

        return new WebResult("200", "删除成功", "");
    }

    /**
     * 课程 启用/停用 页面
     *
     * @param course
     * @return
     */
    @Transactional
    @Override
    public WebResult updateIsenable(EduCourseVo course) {
        String[] courseIds = course.getCourseIds();
        if (courseIds == null || courseIds.length == 0) {
            return new WebResult("400", "请选择课程", "");
        }
        PageCourseVo pageVo = new PageCourseVo();
        for (String s : courseIds) {
            Integer courseId = Integer.valueOf(s);
            course.setCourseId(courseId);
            if (course.getIsenable().equals(1)) {
                //检查是否有相同的课程已经启用
                EduCourse eqName = eduCourseMapper.selectByCourseId(courseId);
                List<EduClass> classList = eduCourseMapper.selectClassByCourseId(eqName.getCourseId());
                WebResult security = security(eqName, classList);
                if (security != null) {
                    return security;
                }
                pageVo.setSchoolId(Integer.valueOf(eqName.getSchoolId()));
                pageVo.setIsenable(course.getIsenable());
                pageVo.setSearch(eqName.getCourseName());
                List<EduCourse> courseList = eduCourseMapper.selectCourseListBySchoolId(pageVo);
                for (EduCourse eduCourse : courseList) {
                    if (eduCourse.getCourseName().equals(eqName.getCourseName())) {
                        if (!eqName.getCourseId().equals(eduCourse.getCourseId())) {
                            return new WebResult("400", "已有“" + eqName.getCourseName() + "”,请先停用！", "");
                        }
                    }
                }
            }
            eduCourseMapper.updateCourseByCourseId(course);
        }
        return new WebResult("200", "修改成功", "");
    }

    @Override
    public void shelvesCourse() {
        Date date = new Date();
        List<EduCourse> courseList = eduCourseMapper.shelvesCourse(date);
        for (EduCourse course : courseList) {
            course.setIsenable(-1);
            eduCourseMapper.updateCourseByCourseId(course);
        }
    }


}
