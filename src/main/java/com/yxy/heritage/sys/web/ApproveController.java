package com.yxy.heritage.sys.web;


import com.yxy.heritage.constant.ConstantClassField;
import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.Approve;
import com.yxy.heritage.sys.bean.User;
import com.yxy.heritage.sys.service.ApproveService;
import com.yxy.heritage.sys.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
  @RequestMapping("/her")
public class ApproveController {
       @Resource
        private ApproveService approveService;
       @Resource
       private RedisUtil redisUtil;
      /**
       * 点赞
       *
       * @param approve
       *
       * @return
       */
      @PostMapping(value = "/approve")
       public WebResult approve(HttpServletRequest request, @RequestBody Approve approve){
          String userId = request.getHeader("userId");
          if(StringUtils.isBlank(userId) || userId.equals("null")) {
              return new WebResult("50012", "请登录！", "");
          }
          return  approveService.approve(Integer.valueOf(userId),approve.getTypeId(),approve.getType());
       }
}
