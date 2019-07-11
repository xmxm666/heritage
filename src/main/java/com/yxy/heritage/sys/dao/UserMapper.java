package com.yxy.heritage.sys.dao;


import com.yxy.heritage.sys.bean.User;
import com.yxy.heritage.sys.vo.PageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Author xzy
 * @Date 2019/4/10 16:04
 * @Version 1.0
 **/
@Mapper
public interface UserMapper {

    //通过id/电话号码查找用户    返回带密码
    public User selectByIdOrPhone(@Param("userId") Integer userId, @Param("phone") String phone);

    //用户注册
    public void registUser(User user);

    //根据手机号修改密码
    public void updatePwdByPhone(@Param("phone") String phone, @Param("password") String password);

    //更换手机号
    public void updatePhone(@Param("oldPhone") String oldPhone, @Param("newPhone") String newPhone);

}
