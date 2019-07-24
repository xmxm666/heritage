package com.yxy.heritage.sys.service.impl;

import com.yxy.heritage.sys.bean.EduOfflineOrderitem;
import com.yxy.heritage.sys.bean.EduOfflineOrderitemExample;
import com.yxy.heritage.sys.dao.EduOfflineOrderitemMapper;
import com.yxy.heritage.sys.service.EduOfflineOrderitemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-24 12:06
 */
@Service
public class EduOfflineOrderitemServiceImpl implements EduOfflineOrderitemService {
    @Resource
    private EduOfflineOrderitemMapper eduOfflineOrderitemMapper;

    @Override
    public int addItemOrder(EduOfflineOrderitem eduOfflineOrderitem) {
        int i = eduOfflineOrderitemMapper.insertSelective(eduOfflineOrderitem);
        return i;
    }

    @Override
    public List<EduOfflineOrderitem> queryAllOffOrderItem(String schoolId, Integer userId) {
        EduOfflineOrderitemExample eduOfflineOrderitemExample = new EduOfflineOrderitemExample();
        eduOfflineOrderitemExample.createCriteria().andUserIdEqualTo(userId).andSchoolIdEqualTo(Integer.valueOf(schoolId));
        List<EduOfflineOrderitem> eduOfflineOrderitems = eduOfflineOrderitemMapper.selectByExample(eduOfflineOrderitemExample);
        return eduOfflineOrderitems;
    }

    @Override
    public Integer updatePayStatus(String schoolId, Integer userId, Integer courseId) {
        return null;
    }

}
