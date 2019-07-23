package com.yxy.heritage.sys.service.impl;

import com.yxy.heritage.constant.ConstantClassField;
import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.User;
import com.yxy.heritage.sys.dao.UserMapper;
import com.yxy.heritage.sys.service.UserService;
import com.yxy.heritage.sys.utils.MD5Utils;
import com.yxy.heritage.sys.utils.RedisUtil;
import com.yxy.heritage.sys.utils.SecurityUtils;
import com.yxy.heritage.sys.vo.PageVo;
import com.yxy.heritage.sys.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.*;


/**
 * @ClassName UserServiceImpl
 * @Author xzy
 * @Date 2019/4/11 16:21
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public WebResult addUser(UserVo user) {
        //验证手机号是否被注册
        User finduserbyphone = userMapper.selectByIdOrPhone(null, user.getPhone());
        if (finduserbyphone != null) {
            return new WebResult("400", "该用户已注册");
        }
        //验证手机号，密码，验证码是否正确
//        WebResult checkPhone = checkPhone(user.getPhone(), user.getPassword(), user.getSecurityCode());
//        if (checkPhone != null) {
//            return checkPhone;
//        }

        if (StringUtils.isBlank(user.getNextPass())) {
            return new WebResult("400", "确认密码不能为空！", "");
        }
        if (!user.getPassword().equals(user.getNextPass())) {
            return new WebResult("400", "输入的两次密码不一致");
        }

        //生成随机盐
        String salt = String.valueOf(new Random().nextInt(8999) + 1000);
        user.setPassword(MD5Utils.md5Password(user.getPassword(), salt));

        user.setSalt(salt);
        user.setUserName(user.getPhone());
        user.setRegistDate(new Date());
        userMapper.registUser(user);
        return new WebResult("200", "注册成功", "");
    }

    //验证手机号，密码，验证码是否正确
    public WebResult checkPhone(String phone, String pwd, String code) {
        //手机号码格式验证
        if (!SecurityUtils.securityPhone(phone)) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        //密码格式验证
        WebResult secPwd = SecurityUtils.securityPwd(pwd);
        if (secPwd != null) {
            return secPwd;
        }
        //短信验证码
        if (StringUtils.isBlank(code)) {
            return new WebResult("400", "验证码不能为空！", "");
        }
        Object value = redisUtil.get(ConstantClassField.SEND_SMS + phone);
        if (value == null) {
            return new WebResult("400", "验证码失效");
        }
        if (!code.equals(value.toString())) {
            return new WebResult("400", "验证码错误");
        }
        return null;
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public WebResult login(User user) {
        //手机号码格式验证
        if (!SecurityUtils.securityPhone(user.getPhone())) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        //验证手机号是否被注册
        User finduserbyphone = userMapper.selectByIdOrPhone(null, user.getPhone());
        if (finduserbyphone == null) {
            return new WebResult("400", "您的手机号尚未注册！", "");
        }
        String pasword = MD5Utils.md5Password(user.getPassword(),finduserbyphone.getSalt());
        if (!pasword.equals(finduserbyphone.getPassword())) {
            return new WebResult("400", "输入的密码不正确！", "");
        }
        HashMap<String, Object> map = new HashMap();
        finduserbyphone.setSalt(null);
        finduserbyphone.setPassword(null);
        map.put("user", finduserbyphone);

        //添加token
        String token = UUID.randomUUID().toString();
        //设置半年缓存过期
        boolean isSet = redisUtil.set(token, finduserbyphone.getUserId(), 6 * 30 * 24 * 60 * 60);
        if (!isSet) {
            return new WebResult("400", "登录失败", "");
        }
        map.put("token", token);
        return new WebResult("200", "登录成功", map);
    }

    /**
     * 修改密码(短信验证码)
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public WebResult updatePassword(@RequestBody UserVo user) {
        //验证手机号，密码，验证码是否正确
        WebResult checkPhone = checkPhone(user.getPhone(), user.getNewPassword(), user.getSecurityCode());
        if (checkPhone != null) {
            return checkPhone;
        }
        if (StringUtils.isBlank(user.getNextPass())) {
            return new WebResult("400", "确认密码不能为空！", "");
        }
        if (!user.getNewPassword().equals(user.getNextPass())) {
            return new WebResult("400", "输入的两次密码不一致");
        }
        //验证手机号是否被注册
        User finduserbyphone = userMapper.selectByIdOrPhone(null, user.getPhone());
        if (finduserbyphone == null) {
            return new WebResult("400", "您的手机号尚未注册！");
        }
        String password = MD5Utils.md5Password(user.getNewPassword(), finduserbyphone.getSalt());
        userMapper.updatePwdByPhone(user.getPhone(), password);
        return new WebResult("200", "修改密码成功", "");
    }

    /**
     * 修改密码 （根据原密码修改）
     * @param user
     * @return
     */
    @Transactional
    @Override
    public WebResult updatePasswordByOldPass(UserVo user) {
        User findUserByOldPass = userMapper.selectByIdOrPhone(user.getUserId(), null);
        if (findUserByOldPass == null) {
            return new WebResult("400", "未找到对应用户");
        }
        if(StringUtils.isBlank(user.getOldPassword())) {
            return new WebResult("400", "原密码不能为空");
        }
        String newPassword = user.getNewPassword();
        String nextPass = user.getNextPass();
        if(StringUtils.isBlank(newPassword) || StringUtils.isBlank(nextPass)) {
            return new WebResult("400", "新密码不能为空");
        }
        String oldPass = MD5Utils.md5Password(user.getOldPassword(), findUserByOldPass.getSalt());
        if(!findUserByOldPass.getPassword().equals(oldPass)) {
            return new WebResult("400", "原密码错误");
        }
        //密码格式验证
        WebResult secPwd = SecurityUtils.securityPwd(newPassword);
        if (secPwd != null) {
            return secPwd;
        }
        if (!newPassword.equals(nextPass)) {
            return new WebResult("400", "输入的两次密码不一致");
        }
        newPassword = MD5Utils.md5Password(newPassword, findUserByOldPass.getSalt());
        userMapper.updatePwdByPhone(findUserByOldPass.getPhone(), newPassword);
        return new WebResult("200", "修改成功", "");
    }

    /**
     * 更换手机号
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public WebResult updatePhone(UserVo user) {
        String oldPhone = user.getOldPhone();
        String newPhone = user.getNewPhone();
        String securityCode = user.getSecurityCode();

        //手机号码格式验证
        if (!SecurityUtils.securityPhone(oldPhone)) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        User findUser = userMapper.selectByIdOrPhone(null, oldPhone);
        if (findUser == null) {
            return new WebResult("400", "未找到对应用户");
        }
        if (!SecurityUtils.securityPhone(newPhone)) {
            return new WebResult("400", "请输入正确的新手机号", "");
        }
        //短信验证码
        if (StringUtils.isBlank(securityCode)) {
            return new WebResult("400", "验证码不能为空！", "");
        }
        Object value = redisUtil.get(ConstantClassField.SEND_SMS + newPhone);
        if (value == null) {
            return new WebResult("400", "验证码失效");
        }
        if (!securityCode.equals(value.toString())) {
            return new WebResult("400", "验证码错误");
        }

        User newUser = userMapper.selectByIdOrPhone(null, newPhone);
        if (newUser != null) {
            return new WebResult("400", "该手机号已被使用");
        }
        userMapper.updatePhone(oldPhone, newPhone);
        return new WebResult("200", "更换成功");
    }

    //退出登录
    @Override
    public WebResult userQuit(String token) {
        redisUtil.delete(token);
        return new WebResult("200", "退出成功", "");
    }
}
