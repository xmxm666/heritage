package com.yxy.heritage.sys.service;

import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.vo.PageVo;

public interface WorksShowService {
     //作品展示列表及模糊查询
     public WebResult findUserShowAll(PageVo pageVo);
     //作品展示首页推荐
    public WebResult findIndexShowAll(PageVo pageVo);
     //作品展示点赞数+1
    public Integer addThumbup(Integer id);
     //名家风采点赞数+1
    public Integer addTeacherThumbup(Integer id);


}