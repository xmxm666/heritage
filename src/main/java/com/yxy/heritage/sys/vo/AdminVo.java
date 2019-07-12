package com.yxy.heritage.sys.vo;

import com.yxy.heritage.sys.bean.Admin;

/**
 *
 * @author vvtxw
 * @create 2019-04-14 7:53
 */
public class AdminVo extends Admin {
    private String schoolName;
    private Integer[] roleIds;

    public Integer[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }

    public AdminVo() {

    }

    public AdminVo(Admin admin) {
        this.setAdminId(admin.getAdminId());
        this.setName(admin.getName());
        this.setPhone(admin.getPhone());
        this.setImage(admin.getImage());
        this.setRole(admin.getRole());
        this.setSchoolId(admin.getSchoolId());
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
