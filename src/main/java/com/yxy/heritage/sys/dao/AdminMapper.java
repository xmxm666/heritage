package com.yxy.heritage.sys.dao;

import com.yxy.heritage.sys.bean.Admin;
import com.yxy.heritage.sys.vo.AdminVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName AdminMapper
 * @Author zqx
 * @Date 2019/4/10 16:04
 * @Version 1.0
 **/
@Mapper
public interface AdminMapper {

    public Admin selectByPhoneOrId(@Param("adminId") Integer adminId, @Param("phone") String phone);

    //查找管理员列表
    public List<AdminVo> selectByAdminList(@Param("schoolId") Integer schoolId);

    public void creatAdmin(Admin admin);

    public void updateByAdmin(Admin admin);

    public void deleteById(@Param("adminId") Integer adminId);

}
