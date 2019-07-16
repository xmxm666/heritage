package com.yxy.heritage.sys.web;


import com.yxy.heritage.constant.ConstantClassField;
import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.Admin;
import com.yxy.heritage.sys.bean.EduArticle;
import com.yxy.heritage.sys.bean.User;
import com.yxy.heritage.sys.service.EduArticleService;
import com.yxy.heritage.sys.service.UserService;
import com.yxy.heritage.sys.utils.RedisUtil;
import com.yxy.heritage.sys.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author vvtxw
 * @create 2019-04-21 20:07
 */

@RestController
public class EduArticleController {

    @Autowired
    private EduArticleService eduArticleService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;

    /**
     * 1-通知公告, 3-校园介绍,  4-联盟资讯,   5-政策法规
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/server/notice")
    public WebResult queryServerAllNotice(HttpServletRequest request, @RequestBody PageVo pageVo) {

        return eduArticleService.queryAllNotice(pageVo);
    }
    /**
     * 1-通知公告, 3-校园介绍,  4-联盟资讯,   5-政策法规
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/her/notice")
    public WebResult queryAllNotice(HttpServletRequest request, @RequestBody PageVo pageVo) {
        String schoolId = request.getHeader("schoolId");
        if(!StringUtils.isBlank(schoolId) && !schoolId.equals("null")) {
            pageVo.setSchoolId(Integer.valueOf(schoolId));
        }
        return eduArticleService.queryAllNotice(pageVo);
    }

    /**
     * 进入文章
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/her/queryNotice")
    public WebResult queryNoticeById(@RequestParam(value = "id", required = true) Integer id) {
        return eduArticleService.queryNoticeById(id);
    }

    /**
     * 后台添加 修改  1-通知公告, 3-校园介绍,  4-联盟资讯,   5-政策法规
     *
     * @param eduArticle
     * @return
     */
    @PostMapping(value = "/server/article/addNotice")
    public WebResult adminAddNotice(HttpServletRequest request, @RequestBody EduArticle eduArticle) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        Integer role = 0;
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                eduArticle.setSchoolId(hget.getSchoolId());
                role = hget.getRole();
            }
            return eduArticleService.adminAddNotice(eduArticle, role);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 进入文章修改页面
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/server/article/findNotice")
    public WebResult findNotice(@RequestParam(value = "id", required = true) Integer id) {
        return eduArticleService.findNotice(id);
    }

    /**
     * 后台查询文章（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/server/article/findNoticeAll")
    public WebResult findNoticeAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                pageVo.setSchoolId(hget.getSchoolId());
            }
            return eduArticleService.findNoticeAll(pageVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }

    /**
     * 后台删除文章
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/server/article/deleteNotice")
    public WebResult deleteNotice(@RequestParam(value = "id") Integer id) {
        return eduArticleService.deleteNotice(id);
    }


}
