package com.yxy.heritage.sys.web;

import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.Address;
import com.yxy.heritage.sys.service.AddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName AddressController
 * @Author zqx
 * @Version 1.0
 **/

@RestController
@RequestMapping("/her/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 添加/修改收货地址
     *
     * @param address
     * @return
     */
    @PostMapping(value = "/createAddress")
    public WebResult createAddress(HttpServletRequest request, @RequestBody Address address) {
        String userId = request.getHeader("userId");
        if(StringUtils.isBlank(userId) || userId.equals("null")) {
            return new WebResult("50012", "请登录！", "");
        }
        address.setUserId(Integer.parseInt(userId));
        return addressService.createAddress(address);
    }

    /**
     * 进入修改收货地址
     *
     * @param addressId
     * @return
     */
    @GetMapping(value = "/findAddress")
    public WebResult findAddress(@RequestParam(value = "addressId") Integer addressId) {
        return addressService.findAddress(addressId);
    }

    /**
     * 收货地址列表
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/addressList")
    public WebResult addressList(HttpServletRequest request) {
        return addressService.addressList(Integer.parseInt(request.getHeader("userId")));
    }

    /**
     * 删除收货地址
     *
     * @param addressId
     * @return
     */
    @GetMapping(value = "/deleteAddress")
    public WebResult deleteAddress(@RequestParam(value = "addressId") Integer addressId) {
        return addressService.deleteAddress(addressId);
    }

}
