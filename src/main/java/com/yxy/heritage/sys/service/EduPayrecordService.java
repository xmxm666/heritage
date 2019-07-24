package com.yxy.heritage.sys.service;

import com.yxy.heritage.sys.bean.EduPayrecord;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-29 17:41
 */
public interface EduPayrecordService {

    //生存支付记录表
    Integer insertPayRecord(EduPayrecord eduPayrecord);

    //根据学员查询所有的记录
    List<EduPayrecord> queryAllRecordByUserId(Integer userId);

    //删除原有的记录
    void deleteAllrecord(Integer id);

    //根据课程id删除记录
    Integer deleterecordByCourseId(Integer courseId);
}
