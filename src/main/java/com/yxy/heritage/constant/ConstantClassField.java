package com.yxy.heritage.constant;

public class ConstantClassField {

    /**
     * redis  pc端token缓存过期时间   //一天
     */
    public static final long PC_CACHE_EXPIRATION_TIME = 24 * 60 * 60;

    /**
     * redis  单点登录功能key   single sign on   SSO
     */
    public static final String SINGLE_SIGN_ON = "SSO_ADMIN";
    /**
     * redis  app单点登录功能key   single sign on   SSO
     */
    public static final String SINGLE_SIGN_ON_USER = "SSO_USER";

    /**
     * redis        用户信息
     */
    public static final String ADMIN_INFO = "ADMIN_INFO";
    /**
     * redis        app端用户信息
     */
    public static final String USER_INFO = "USER_INFO";

    /**
     * redis    用户权限
     */
    public static final String ADMIN_PERMISSION = "ADMIN_PERMISSION";

    /**
     * 管理员状态  0-超级管理员
     */
    public static final Integer ROLE_SUPERADMIN_STATUS = 0;

    /**
     * 管理员状态  1-管理员
     */
    public static final Integer ROLE_ADMIN_STATUS = 1;

    /**
     * 管理员状态  2-教师
     */
    public static final Integer ROLE_TEACHER_STATUS = 2;

    /**
     * 上线课程
     */
    public static final Integer ISONLINE = 1;

    /**
     *  在校任职    宣传员
     */
    public static final String SCHOOL_JOB_XCY = "宣传员";

    /**
     *  在校任职    物资管理员
     */
    public static final String SCHOOL_JOB_WZGLY  = "物资管理员";

    /**
     *  发送短信验证码， 报存至redis  key
     */
    public static final String SEND_SMS  = "her_send_sms_";
}
