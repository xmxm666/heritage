package com.yxy.heritage.sys.web;

import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.EduArticle;
import com.yxy.heritage.sys.service.EduArticleService;
import com.yxy.heritage.sys.service.WorksShowService;
import com.yxy.heritage.sys.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName WorksShowController
 * 注意：1.App端作品展示(包括作品展示、名家风采)
 *      2.名家风采、作品展示均是用的edu_article表，通过type区分
 * @Author xzy
 * @Date 2019/5/21 15:45
 * @Version 1.0
 **/
@RestController
public class WorksShowController {
     @Resource
     private EduArticleService eduArticleService;
     @Resource
     private WorksShowService worksShowService;

    /**
     * 作品展示列表及模糊查询（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/her/findUserShowAll")
    public WebResult findUserShowAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
            pageVo.setType("7");
        return worksShowService.findUserShowAll(pageVo);

}
    /**
     * 作品展示列表及模糊查询（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/server/findUserShowAll")
    public WebResult serverUserShowAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
        pageVo.setType("7");
        return worksShowService.findUserShowAll(pageVo);

    }

    /**
     * 名家风采列表及模糊查询（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/her/findTeacherShowAll")
    public WebResult findTeacherShowAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
            pageVo.setType("8");
            return worksShowService.findUserShowAll(pageVo);

}

    /**
     * 名家风采列表及模糊查询（分页）
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/server/findTeacherShowAll")
    public WebResult serverTeacherShowAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
        pageVo.setType("8");
        return worksShowService.findUserShowAll(pageVo);

    }

    /**
     * 作品展示首页推荐
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/her/findIndexShowAll")
    public WebResult findIndexShowAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
        pageVo.setType("7");
        return worksShowService.findIndexShowAll(pageVo);

    }
    /**
     * 名家风采首页推荐
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/her/TeacherIndexShowAll")
    public WebResult TeacherIndexShowAll(HttpServletRequest request, @RequestBody PageVo pageVo) {
        pageVo.setType("8");
        return worksShowService.findIndexShowAll(pageVo);

    }

    /**
     * 进入作品展示详情页
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/her/queryUserShowById")
    public WebResult queryUserShowById(@RequestParam(value = "id", required = true) Integer id) {
        return eduArticleService.queryNoticeById(id);
    }

    /**
     * 进入作品详情页（后台）
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/server/queryUserShowById")
    public WebResult serverUserShowById(@RequestParam(value = "id", required = true) Integer id) {
        return eduArticleService.queryNoticeById(id);
    }

    /**
     * 进入名家风采详情页
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/her/queryTeacherShowById")
    public WebResult queryTeacherShowById(@RequestParam(value = "id", required = true) Integer id) {
        return eduArticleService.queryNoticeById(id);
    }

    /**
     * 进入名家风采详情页（后台）
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/server/queryTeacherShowById")
    public WebResult serverTeacherShowById(@RequestParam(value = "id", required = true) Integer id) {
        return eduArticleService.queryNoticeById(id);
    }

    /**
     * 后台添加、修改作品展示
     *
     * @param eduArticle
     * @return
     */
    @PostMapping(value = "/server/WorksShow/updateUserShowById")
    public WebResult updateUserShowById(@RequestBody EduArticle eduArticle) {
        //后台修改学员展示作品

        return eduArticleService.adminAddNotice(eduArticle,1);
    }
    /**
     * 后台添加、修改教名家风采
     *
     * @param eduArticle
     * @return
     */
    @PostMapping(value = "/server/WorksShow/updateTeacherShowById")
    public WebResult updateTeacherShowById(@RequestBody EduArticle eduArticle) {
        //后台修改教师风采作品
        return eduArticleService.adminAddNotice(eduArticle,1);
    }
    /**
     * 后台删除一条学员展示
     *
     * @param eduArticle
     * @return
     */
    @PostMapping(value = "/server/WorksShow/deleteUserShowById")
    public WebResult deleteUserShowById(@RequestBody EduArticle eduArticle) {
        //后台删除作品展示作品
        return eduArticleService.deleteNotice(eduArticle.getId());
    }
    /**
     * 后台删除一条名家风采记录
     *
     * @param eduArticle
     * @return
     */
    @PostMapping(value = "/server/WorksShow/deleteTeacherShowById")
    public WebResult deleteTeacherShowById(@RequestBody EduArticle eduArticle) {
        //后台删除作品展示作品
        return eduArticleService.deleteNotice(eduArticle.getId());
    }
}