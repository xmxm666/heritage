package com.yxy.heritage.sys.service;

import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.Address;

public interface AddressService {

    public WebResult createAddress(Address address);

    public WebResult findAddress(Integer addressId);

    public WebResult addressList(Integer userId);

    public WebResult deleteAddress(Integer addressId);
}
