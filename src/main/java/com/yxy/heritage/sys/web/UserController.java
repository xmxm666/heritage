
package com.yxy.heritage.sys.web;

import com.jfinal.aop.Before;

import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.User;
import com.yxy.heritage.sys.service.UserService;
import com.yxy.heritage.sys.vo.PageVo;
import com.yxy.heritage.sys.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 *用户模块
 * Created by looyer on 2019/4/2.
 */
@RestController
@RequestMapping("/her/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/regist")
    public WebResult regist(@RequestBody UserVo user) {
        return userService.addUser(user);
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/login")
    public WebResult login(@RequestBody User user) {
        return userService.login(user);
    }

    /**
     * 修改密码(短信验证码)
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/updatePassword")
    public WebResult updatePassword(@RequestBody UserVo user) {
        return userService.updatePassword(user);
    }

    /**
     * 修改密码（通过原密码）
     *
     * @param
     * @return
     */
    @PostMapping(value = "/updatePasswordByOldpass")
    public WebResult updatePasswordByOldpass(@RequestBody UserVo user, HttpServletRequest request) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId)) {
            return new WebResult("400", "请登录！", "");
        }
        user.setUserId(Integer.valueOf(userId));
        return userService.updatePasswordByOldPass(user);
    }

    /**
     * 变更手机
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/updatePhone")
    public WebResult updatePhone(@RequestBody UserVo user) {
        return userService.updatePhone(user);
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/userQuit")
    public WebResult userQuit(HttpServletRequest request) {
        String token = request.getHeader("token");
        return userService.userQuit(token);
    }



}
