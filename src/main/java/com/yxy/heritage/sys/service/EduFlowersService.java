package com.yxy.heritage.sys.service;

import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.EduFlowers;

/**
 * @author zqx
 * @since 2019-07-04
 */
public interface EduFlowersService {

    public WebResult totalFlower(Integer userId);

    public WebResult flowerList(Integer userId);

    public void createFlower(EduFlowers eduFlowers);
}
