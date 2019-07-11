package com.yxy.heritage.sys.service;


import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.User;
import com.yxy.heritage.sys.vo.PageVo;
import com.yxy.heritage.sys.vo.UserVo;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    //用户注册
    public WebResult addUser(UserVo user);

    //查找用户
    public WebResult login(User user);

    //修改密码（通过手机号）
    public WebResult updatePassword(@RequestBody UserVo user);

    //修改密码（通过原密码）
    public WebResult updatePasswordByOldPass(UserVo user);

    //变更手机
    public WebResult updatePhone(UserVo user);

    //退出登录
    public WebResult userQuit(String token);
}
