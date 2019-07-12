package com.yxy.heritage.sys.web;

import com.yxy.heritage.constant.ConstantClassField;
import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.Admin;
import com.yxy.heritage.sys.service.AdminService;
import com.yxy.heritage.sys.service.UserService;
import com.yxy.heritage.sys.utils.RedisUtil;
import com.yxy.heritage.sys.vo.AdminVo;
import com.yxy.heritage.sys.vo.PageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName AdminController
 * @Author zqx
 * @Date 2019/4/10 15:45
 * @Version 1.0
 **/

@RestController
@RequestMapping("/server/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisUtil redisUtil;


    /**
     * 登录
     *
     * @param admin
     * @return
     */
    @PostMapping(value = "/login")
    public WebResult login(@RequestBody Admin admin) {
        return adminService.login(admin);
    }

    /**
     * 管理员列表
     *
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/adminList")
    public WebResult adminList(HttpServletRequest request, @RequestBody PageVo pageVo) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                pageVo.setSchoolId(hget.getSchoolId());
            }
            return adminService.adminList(pageVo);
        }else {
            return new WebResult("50012", "请重新登录", "");
        }
    }

    /**
     * 添加/修改 管理员(带角色)
     *
     * @param adminVo
     * @return
     */
    @PostMapping(value = "/createAdminRole")
    public WebResult createAdminRole(HttpServletRequest request, @RequestBody AdminVo adminVo) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        Integer id = (Integer) o;
        Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
        if(hget.getRole() != 0) {
            adminVo.setSchoolId(hget.getSchoolId());
        }
        return adminService.createAdminRole(adminVo);
    }

    /**
     * 进入修改 管理员页面
     *
     * @param adminId
     * @return
     */
    @GetMapping(value = "/findAdmin")
    public WebResult findAdmin(@RequestParam(value = "adminId") Integer adminId) {
        return adminService.findAdmin(adminId);
    }

    /**
     * 删除管理员
     *
     * @param adminId
     * @return
     */
    @GetMapping(value = "/deleteAdmin")
    public WebResult deleteAdmin(@RequestParam(value = "adminId") Integer adminId, Integer schoolId) {
        return adminService.deleteAdmin(adminId, schoolId);
    }
}
