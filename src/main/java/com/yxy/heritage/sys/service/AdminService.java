package com.yxy.heritage.sys.service;

import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.Admin;
import com.yxy.heritage.sys.vo.AdminVo;
import com.yxy.heritage.sys.vo.PageVo;

public interface AdminService {

    public WebResult login(Admin admin);

    public WebResult findAdmin(Integer adminId);

    public WebResult deleteAdmin(Integer adminId, Integer schoolId);

    public WebResult adminList(PageVo pageVo);

    //添加 管理员(带角色)
    public WebResult createAdminRole(AdminVo adminVo);

}
