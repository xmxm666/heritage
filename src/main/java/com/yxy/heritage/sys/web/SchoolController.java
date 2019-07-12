package com.yxy.heritage.sys.web;

import com.yxy.heritage.constant.ConstantClassField;
import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.Admin;
import com.yxy.heritage.sys.bean.School;
import com.yxy.heritage.sys.service.SchoolService;
import com.yxy.heritage.sys.utils.RedisUtil;
import com.yxy.heritage.sys.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SchoolController
 * @Author zqx
 **/

@RestController
public class SchoolController {
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 添加学校
     *
     * @param school
     * @return
     */
    @PostMapping(value = "/server/school/create")
    public WebResult createAdmin(HttpServletRequest request, @RequestBody School school) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                return new WebResult("400", "暂无该权限", "");
            }
            return schoolService.createSchool(school);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 进入修改学校页面
     *
     * @param schoolId
     * @return
     */
    @GetMapping(value = "/server/school/findSchool")
    public WebResult findSchool(@RequestParam(value = "schoolId") Integer schoolId) {
        return schoolService.findSchool(schoolId);
    }

    /**
     * 修改学校
     *
     * @param school
     * @return
     */
    @PostMapping(value = "/server/school/updateSchool")
    public WebResult updateSchool(@RequestBody School school) {
        return schoolService.updateSchool(school);
    }

    /**
     * 学校列表
     *
     * @param request
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/server/school/schoolList")
    public WebResult schoolList(HttpServletRequest request, @RequestBody PageVo pageVo) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                pageVo.setSchoolId(hget.getSchoolId());
            }
            return schoolService.schoolList(pageVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 删除学校
     *
     * @param schoolId
     * @return
     */
    @GetMapping(value = "/server/school/deleteSchool")
    public WebResult deleteSchool(@RequestParam(value = "schoolId") Integer schoolId) {
        return schoolService.deleteSchool(schoolId);
    }
    /**
     * 查阅指定学校轮播图
     * @param request
     * @return
     */
    @GetMapping(value = "/school/findSlideshowAll")
    public WebResult findSlideshowAll(HttpServletRequest request) {
        School school = schoolService.findSlideshowAll(Integer.parseInt(request.getHeader("schoolId")));
        String slideshow = school.getSlideshow();
        if (slideshow==null){
            return  new WebResult("400","获取轮播图资源为空", "");
        }
        String[] strs = slideshow.split(",");
        return  new WebResult("200","获取成功", strs);
    }

    /**
     * 查询所有学校（学校名字和学校Id）
     *
     * @param
     * @return
     */
    @GetMapping(value = "/school/findSchoolAll")
    public WebResult findSchoolAll() {
        List<Map<String, String>> schoolAll = schoolService.findSchoolAll();
        return new WebResult("200","查询成功",schoolAll);
    }
    /**
     * 查询对应学校（通过学校id）
     *
     * @param
     * @return
     */
    @GetMapping(value = "/school/findSchoolById")
    public WebResult findSchoolById(HttpServletRequest request) {
        String school = schoolService.findSchoolById(Integer.parseInt(request.getHeader("schoolId")));
           return  new WebResult("200","查询学校成功",school);

    }
}
