package com.yxy.heritage.sys.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class EduClass implements Serializable {
    private Integer classScheduleId;

    /**
     * 课程id
     */
    private Integer courseIds;

    /**
     * 课时id
     */
    private String week;

    private String startTime;

    private String endTime;

    private static final long serialVersionUID = 1L;

    public Integer getClassScheduleId() {
        return classScheduleId;
    }

    public void setClassScheduleId(Integer classScheduleId) {
        this.classScheduleId = classScheduleId;
    }

    public Integer getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(Integer courseIds) {
        this.courseIds = courseIds;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}