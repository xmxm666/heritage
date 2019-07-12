package com.yxy.heritage.sys.service;

import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.Role;
import com.yxy.heritage.sys.vo.PageVo;

import java.util.HashSet;

public interface RoleService {

    public HashSet<String> selectByAdminId(Integer adminId, Integer schoolId);

    public WebResult allMenu(Integer adminId, Integer schoolId, Integer role);

    public WebResult roleList(PageVo pageVo);

    public WebResult createRole(Role role);

    public WebResult findRole(Integer roleId);

    public WebResult deleteRole(Integer roleId);

    public WebResult allPermission();

    public WebResult addAdminRole(Integer[] roleIds, Integer adminId, Integer schoolId);
}
