package com.yxy.heritage.sys.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Author zqx
 * @Date 2019/4/9 10:13
 * @Version 1.0
 **/
public class User implements Serializable {

    private Integer userId;
    private String userName;
    /**
     * 0-女 1-男
     */
    private Integer sex;
    private String password;
    private Integer schoolId;
    private String phone;
    /**
     * 固定电话
     */
    private String telephone;

    /**
     * 居住地
     */
    private String area;

    /**
     * 地址
     */
    private String address;

    /**
     * 政治面貌
     */
    private String political;

    /**
     * 文化程度
     */
    private String educational;

    //身份证
    private String identityCard;

    //用户头像
    private String image;

    //个性签名
    private String mycontext;

    //密码加盐
    private String salt;

    //注册时间
    private Date registDate;

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getEducational() {
        return educational;
    }

    public void setEducational(String educational) {
        this.educational = educational;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMycontext() {
        return mycontext;
    }

    public void setMycontext(String mycontext) {
        this.mycontext = mycontext;
    }
}
