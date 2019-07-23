package com.yxy.heritage.sys.service;


import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.EduComment;
import com.yxy.heritage.sys.vo.PageComVo;

public interface CommentService {

    //评论
    public WebResult addComment(EduComment comment);

    //评论列表
    public WebResult commentList(PageComVo pageComVo);

    //删除评论
    public WebResult deleteComment(Integer comid);
}
