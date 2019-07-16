package com.yxy.heritage.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.EduArticle;
import com.yxy.heritage.sys.dao.EduArticleMapper;
import com.yxy.heritage.sys.dao.WorksShowMapper;
import com.yxy.heritage.sys.service.WorksShowService;
import com.yxy.heritage.sys.vo.EduArticleVo;
import com.yxy.heritage.sys.vo.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WorksShowServiceImpl implements WorksShowService {
    @Resource
    private WorksShowMapper worksShowMapper;
    @Resource
    private EduArticleMapper eduArticleMapper;
    //作品展示列表及模糊查询
    @Override
    public WebResult findUserShowAll(PageVo pageVo) {
        if(pageVo.getPageSize() == 0) {
            List<EduArticleVo> noticeList =  worksShowMapper.findUserShowAll(null, pageVo.getSchoolId(), pageVo.getType(),pageVo.getSearch());
            return new WebResult("200", "查询成功", noticeList);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<EduArticleVo> noticeList =  worksShowMapper.findUserShowAll(null, pageVo.getSchoolId(), pageVo.getType(), pageVo.getSearch());
        PageInfo pageInfo = new PageInfo<>(noticeList);
        return new WebResult("200", "查询成功", pageInfo);
    }
    //首页作品展示（教师风采）推荐
    @Override
    public WebResult findIndexShowAll(PageVo pageVo) {
        if(pageVo.getPageSize() == 0) {
            List<EduArticleVo> noticeList =  worksShowMapper.findIndexShowAll(null, pageVo.getSchoolId(), pageVo.getType(),pageVo.getSearch());
            return new WebResult("200", "查询成功", noticeList);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<EduArticleVo> noticeList =  worksShowMapper.findIndexShowAll(null, pageVo.getSchoolId(), pageVo.getType(), pageVo.getSearch());
        PageInfo pageInfo = new PageInfo<>(noticeList);
        return new WebResult("200", "查询成功", pageInfo);
    }
    //添加点赞数
    @Transactional
    @Override
    public Integer addThumbup(Integer id) {

        EduArticle eduArticle = eduArticleMapper.selectAriticleById(id);
        eduArticle.setThumbup(eduArticle.getThumbup()+1);
        eduArticleMapper.updateNoticeThumbup(eduArticle.getId(),eduArticle.getThumbup());
        return eduArticle.getThumbup();
    }

    @Override
    public Integer addTeacherThumbup(Integer id) {
        EduArticle eduArticle = eduArticleMapper.selectAriticleById(id);
        eduArticle.setThumbup(eduArticle.getThumbup()+1);
        eduArticleMapper.updateNoticeThumbup(eduArticle.getId(),eduArticle.getThumbup());
        return eduArticle.getThumbup();
    }
}
