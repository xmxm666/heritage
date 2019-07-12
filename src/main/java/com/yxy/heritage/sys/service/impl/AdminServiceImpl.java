package com.yxy.heritage.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yxy.heritage.constant.ConstantClassField;
import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.Admin;
import com.yxy.heritage.sys.bean.Role;
import com.yxy.heritage.sys.dao.AdminMapper;
import com.yxy.heritage.sys.dao.RoleMapper;
import com.yxy.heritage.sys.service.AdminService;
import com.yxy.heritage.sys.service.RoleService;
import com.yxy.heritage.sys.utils.MD5Utils;
import com.yxy.heritage.sys.utils.RedisUtil;
import com.yxy.heritage.sys.utils.SecurityUtils;
import com.yxy.heritage.sys.vo.AdminVo;
import com.yxy.heritage.sys.vo.PageVo;
import com.yxy.heritage.sys.web.AdminController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName AdminServiceImpl
 * @Author zqx
 * @Date 2019/4/10 16:01
 * @Version 1.0
 **/
@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Resource
    private AdminMapper adminMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RoleService roleService;
    @Resource
    private RoleMapper roleMapper;

    /**
     * 登录
     *
     * @return
     */
    @Override
    public WebResult login(Admin admin) {
        if (!SecurityUtils.securityPhone(admin.getPhone())) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        if (StringUtils.isBlank(admin.getPassword())) {
            return new WebResult("400", "密码不能为空", "");
        }
        //查询该手机号是否已经存在
        Admin adminPhone = adminMapper.selectByPhoneOrId(null, admin.getPhone());
        if (adminPhone == null) {
            return new WebResult("400", "手机号未注册", "");
        }
        String pasword = MD5Utils.md5Password(admin.getPassword(), adminPhone.getSalt());
        if (!pasword.equals(adminPhone.getPassword())) {
            return new WebResult("400", "密码错误", "");
        }
        HashMap<String, Object> map = new HashMap();
        map.put("admin", adminPhone);

        //添加token
        String token = UUID.randomUUID().toString();
        //pc端设置半个小时缓存过期
        boolean isSet = redisUtil.set(token, adminPhone.getAdminId(), ConstantClassField.PC_CACHE_EXPIRATION_TIME);
        if (!isSet) {
            return new WebResult("400", "登录失败", "");
        }
        map.put("token", token);

        //单点登录功能 single sign on   SSO
        Object hget = redisUtil.hget(ConstantClassField.SINGLE_SIGN_ON, adminPhone.getAdminId().toString());
        if(hget != null) {
            String oldToken = (String) hget;
            redisUtil.delete(oldToken);
        }
        redisUtil.hset(ConstantClassField.SINGLE_SIGN_ON, adminPhone.getAdminId().toString(), token);

        //存放用户信息
        boolean bo = redisUtil.hset(ConstantClassField.ADMIN_INFO, adminPhone.getAdminId().toString(), adminPhone);
        //存放用户权限
        HashSet<String> permission = roleService.selectByAdminId(adminPhone.getAdminId(), adminPhone.getSchoolId());
        redisUtil.hset(ConstantClassField.ADMIN_PERMISSION, adminPhone.getAdminId().toString(), permission);

        /*if(adminPhone.getSchoolId() != null) {
            School school = schoolMapper.selectById(adminPhone.getSchoolId());
            map.put("schoolName", school.getSchoolName());
        } else {
            map.put("schoolName", "");
        }*/

        WebResult result = new WebResult("200", "登录成功", map);
        return result;
    }

    @Override
    public WebResult findAdmin(Integer adminId) {
        Admin adminPhone = adminMapper.selectByPhoneOrId(adminId, null);
        AdminVo admin = new AdminVo(adminPhone);
        //获取角色
        if(admin.getRole() == 3) {
            List<Role> roles = roleMapper.selectByAdminIdList(adminId, admin.getSchoolId());
            Integer[] roleIds = new Integer[roles.size()];
            for (int i = 0; i < roles.size(); i++) {
                roleIds[i] = roles.get(i).getRoleId();
            }
            admin.setRoleIds(roleIds);
        }
        return new WebResult("200", "查询成功",admin);
    }

    /**
     * 删除管理员
     * @param adminId
     * @return
     */
    @Transactional
    @Override
    public WebResult deleteAdmin(Integer adminId, Integer schoolId) {
        //删除 用户-角色
        roleMapper.deleteAdminRole(adminId, null, null);
        adminMapper.deleteById(adminId);
        return new WebResult("200", "删除成功", "");
    }

    /**
     * 管理员列表
     *
     * @param pageVo
     * @return
     */
    @Override
    public WebResult adminList(PageVo pageVo) {
        Integer schoolId = pageVo.getSchoolId();
        if (pageVo.getPageSize() == 0) {
            List<AdminVo> adminList = adminMapper.selectByAdminList(schoolId);
            return new WebResult("200", "查询成功", adminList);
        }
        PageHelper.startPage(pageVo.getPageNum(), pageVo.getPageSize());
        List<AdminVo> adminList = adminMapper.selectByAdminList(schoolId);
        PageInfo pageInfo = new PageInfo<>(adminList);
        return new WebResult("200", "查询成功", pageInfo);
    }

    //添加 管理员(带角色)
    @Transactional
    @Override
    public WebResult createAdminRole(AdminVo admin) {
        if (StringUtils.isBlank(admin.getName())) {
            return new WebResult("400", "姓名不能为空", "");
        }
        if (!SecurityUtils.securityPhone(admin.getPhone())) {
            return new WebResult("400", "请输入正确的手机号", "");
        }
        if(admin.getRole() == null) {
            return new WebResult("400", "请选择角色", "");
        }
        if(!admin.getRole().equals(0)) {
            //添加 学校管理员时学校id不能为空
            if (admin.getSchoolId() == null) {
                return new WebResult("400", "请选择学校", "");
            }
            //添加人员（role = 3）时角色列表不能为空
            if(admin.getRole().equals(3)){
                if(admin.getRoleIds() == null || admin.getRoleIds().length == 0) {
                    return new WebResult("400", "请选择角色", "");
                }
            }
        }else {
            admin.setSchoolId(null);
        }
        Admin adminPhone = adminMapper.selectByPhoneOrId(null, admin.getPhone());
        //添加管理员
        if(admin.getAdminId() == null) {
            if(adminPhone != null) {
                return new WebResult("400", "手机号已注册", "");
            }
            //初始密码为000000
            String password = "000000";
            if(!StringUtils.isBlank(admin.getPassword())) {
                password = admin.getPassword();
            }
            //生成随机盐
            String salt = String.valueOf(new Random().nextInt(8999) + 1000);
            admin.setPassword(MD5Utils.md5Password(password, salt));
            admin.setSalt(salt);
            admin.setRegistDate(new Date());
            adminMapper.creatAdmin(admin);
        } else {
            //查询该手机号是否已经存在
            if (adminPhone != null && adminPhone.getAdminId() != admin.getAdminId()) {
                return new WebResult("400", "手机号已注册", "");
            }
            if(!StringUtils.isBlank(admin.getPassword())) {
                admin.setPassword(MD5Utils.md5Password(admin.getPassword(),adminPhone.getSalt()));
            }
            adminMapper.updateByAdmin(admin);
        }
        if(admin.getRole().equals(3)){
            roleService.addAdminRole(admin.getRoleIds(), admin.getAdminId(), admin.getSchoolId());
        }
        return new WebResult("200", "操作成功", "");
    }


}
