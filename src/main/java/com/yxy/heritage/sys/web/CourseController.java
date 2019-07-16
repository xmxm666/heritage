package com.yxy.heritage.sys.web;


import com.yxy.heritage.constant.ConstantClassField;
import com.yxy.heritage.http.result.StatusCode;
import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.Admin;
import com.yxy.heritage.sys.bean.EduCourse;
import com.yxy.heritage.sys.service.EduCourseService;
import com.yxy.heritage.sys.service.UserService;
import com.yxy.heritage.sys.utils.RedisUtil;
import com.yxy.heritage.sys.vo.EduCourseVo;
import com.yxy.heritage.sys.vo.PageCourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zqx
 * @create 2019-04-14 7:43
 */

@RestController
@RequestMapping("/server/course")
public class CourseController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private EduCourseService courseService;

    /**
     * 课程列表
     * @param pageCourseVo
     * @return
     */
    @PostMapping(value = "/courseList")
    public WebResult courseList(HttpServletRequest request, @RequestBody PageCourseVo pageCourseVo) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                pageCourseVo.setSchoolId(hget.getSchoolId());
            }
            return  courseService.courseList(pageCourseVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 课程 启用/停用 功能
     * @param course
     * @return
     */
    @PostMapping(value = "/updateIsenable")
    public WebResult updateIsenable(@RequestBody EduCourseVo course) {
        return courseService.updateIsenable(course);
    }

    /**
     * 添加/修改 课程
     * @param courseVo
     * @return
     */
    @PostMapping(value = "/createCourse")
    public WebResult createCourse(HttpServletRequest request, @RequestBody EduCourseVo courseVo) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                courseVo.setSchoolId(hget.getSchoolId().toString());
            }
            return  courseService.createCourse(courseVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 进入修改课程页面
     * @param courseId
     * @return
     */
    @GetMapping(value = "/findCourse")
    public WebResult findCourse(@RequestParam(value = "courseId")Integer courseId) {
        return courseService.findCourse(courseId);
    }

    /**
     * 删除课程
     * @param courseVo
     * @return
     */
    @PostMapping(value = "/deleteCourse")
    public WebResult deleteCourse(@RequestBody EduCourseVo courseVo) {
        return courseService.deleteCourse(courseVo.getCourseIds());
    }


}

