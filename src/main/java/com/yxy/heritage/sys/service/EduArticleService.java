package com.yxy.heritage.sys.service;


import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.EduArticle;
import com.yxy.heritage.sys.vo.PageVo;

/**
 * @author vvtxw
 * @create 2019-04-21 19:58
 */
public interface EduArticleService {
    /**
     * 展示通知公告
     *
     * @param  pageVo
     * @return
     */
    public WebResult queryAllNotice(PageVo pageVo);
    /**
     * 后台添加文章
     *
     * @param eduArticle
     * @return
     */
    public WebResult adminAddNotice(EduArticle eduArticle, Integer role);
    /**
     * 后台查询所有文章
     *
     * @param pageVo
     * @return
     */
    public WebResult findNoticeAll(PageVo pageVo);
    /**
     *  后台删除文章
     *
     * @param id
     * @return
     */
    public WebResult deleteNotice(Integer id);
    /**
     *  后台批量删除文章
     *
     * @param ids
     * @return
     */
    public WebResult bigDeleteNotice(Integer[] ids);

    /**
     * 进入文章修改页面
     * @param id
     * @return
     */
    public WebResult findNotice(Integer id);

    /**
     * 进入文章
     * @param id
     * @return
     */
    public WebResult queryNoticeById(Integer id);

    /**
     * 文章置顶的定时任务
     */
    public void topScheduleTask();

    /**
     *  后台删除文章
     *
     * @param cid
     * @return
     */
    public WebResult deleteNoticeByCid(Integer cid);
    /**
     *  后台查询文章
     *
     * @param cid
     * @return
     */
    public EduArticle findNoticeByCid(Integer cid);

}
