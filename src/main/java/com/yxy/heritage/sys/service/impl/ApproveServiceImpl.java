package com.yxy.heritage.sys.service.impl;

import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.Approve;
import com.yxy.heritage.sys.dao.ApproveMapper;
import com.yxy.heritage.sys.service.ApproveService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName ApproveServiceImpl
 * @Author zqx
 * @Version 1.0
 **/
@Service
public class ApproveServiceImpl implements ApproveService {

    private static final Logger logger = LoggerFactory.getLogger(ApproveServiceImpl.class);

    @Resource
    private ApproveMapper approveMapper;


    @Override
    public WebResult approve(Integer userId, Integer typeId, Integer type) {
        //每天只能点赞一次
        Approve approve = approveMapper.selectByType(userId, typeId, type);

        if(approve == null) {
            approve=new Approve();
            approve.setUserId(userId);
            approve.setType(type);
            approve.setTypeId(typeId);
            approve.setCount(1);
            approve.setDate(new Date());
            approveMapper.createApprove(approve);
        } else {
            //判断两个Date是不是同一天
//            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
//            boolean equals = fmt.format(approve.getDate()).equals(fmt.format(new Date()));
            boolean samedate = DateUtils.isSameDay(approve.getDate(), new Date());
            if(samedate) {
                return new WebResult("400", "今天已点过赞了哟！", "");
            }
            approve.setDate(new Date());
            approve.setCount(approve.getCount() + 1);
            approveMapper.updateApproveCount(approve);
        }
        //todo   待添加各种类型的点赞总数

        return new WebResult("200", "点赞成功", "");
    }
}
