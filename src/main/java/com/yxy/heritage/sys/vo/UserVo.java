package com.yxy.heritage.sys.vo;

import com.yxy.heritage.sys.bean.User;

import java.io.Serializable;

/**
 * @ClassName User
 * @Author zqx
 **/
public class UserVo extends User {

    //确认密码
    private String nextPass;

    //短信验证码
    private String securityCode;

    //	新密码
    private String newPassword;

    // 旧密码
    private String oldPassword;

    //新手机号
    private String newPhone;

    //原手机号
    private String oldPhone;

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public String getOldPhone() {
        return oldPhone;
    }

    public void setOldPhone(String oldPhone) {
        this.oldPhone = oldPhone;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNextPass() {
        return nextPass;
    }

    public void setNextPass(String nextPass) {
        this.nextPass = nextPass;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}
