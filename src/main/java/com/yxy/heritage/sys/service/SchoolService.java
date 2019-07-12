package com.yxy.heritage.sys.service;

import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.School;
import com.yxy.heritage.sys.vo.PageVo;

import java.util.List;
import java.util.Map;

public interface SchoolService {
    public WebResult createSchool(School school);

    public WebResult findSchool(Integer schoolId);

    public WebResult updateSchool(School school);
    /**
     * 根据学校id查询坐标
     * @param schoolId
     * @return
     */
    public School queryRecordBySchoolId(Integer schoolId);
    //找到所有学校
    public WebResult switchSchool();
     //更换学校ID
    public WebResult switchSchoolID(Integer SchoolId, Integer userId);

    public WebResult schoolList(PageVo pageVo);

    public WebResult deleteSchool(Integer schoolId);
    //查找学校对应轮播图
    public School findSlideshowAll(Integer schoolId);
    //查询所有学校
    public List<Map<String, String>> findSchoolAll();
    //根据学校id查找对应学校
    public String findSchoolById(Integer schoolId);

}
