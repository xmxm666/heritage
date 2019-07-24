package com.yxy.heritage.sys.web;

import com.yxy.heritage.http.result.StatusCode;
import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.EduOfflinePayInfo;
import com.yxy.heritage.sys.service.EduOfflinePayInfoService;
import com.yxy.heritage.sys.vo.PageBean;
import com.yxy.heritage.sys.vo.QueryOffLineVos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author vvtxw
 * @create 2019-05-27 10:18
 */

@RestController
@RequestMapping("payoff")
public class EduOfflinePayInfoController {

    @Autowired
    private EduOfflinePayInfoService eduOfflinePayInfoService;

    /**
     * 线下分页列表显示
     *
     * @param page
     * @param size
     * @param schoolId
     * @param schoolName
     * @param telephone
     * @param payStatus
     * @return
     */
    @GetMapping("shows")
    public WebResult showsQueryOffline(@RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size, Integer schoolId, String schoolName, String telephone, String payStatus) {
        PageBean pageBean = eduOfflinePayInfoService.queryAllOffInfo(page, size, schoolId, schoolName, telephone, payStatus);
        return new WebResult(StatusCode.OK, "查询成功", pageBean);
    }


    /**
     * 下载列表
     *
     * @param schoolId
     * @param schoolName
     * @param telephone
     * @param payStatus
     * @return
     */
    @GetMapping("donwshows")
    public WebResult donwShowsQueryOffline(Integer schoolId, String schoolName, String telephone, String payStatus) {
        List<QueryOffLineVos> queryOffLineVos = eduOfflinePayInfoService.queryAllOff(schoolId, schoolName, telephone, payStatus);
        return new WebResult(StatusCode.OK, "查询成功", queryOffLineVos);
    }

    /**
     * 单个缴费
     *
     * @param eduOfflinePayInfo
     * @return
     */
    @PostMapping(value = "updatepaystatus")
    public WebResult updatePayStatus(@RequestBody EduOfflinePayInfo eduOfflinePayInfo) {
        Integer res = eduOfflinePayInfoService.updatePayStatus(eduOfflinePayInfo);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "修改成功", "");
        }
        return new WebResult(StatusCode.ERROR, "修改失败", "");
    }

    /**
     * 批量缴费
     *
     * @param eduOfflinePayInfo
     * @return
     */
    @PostMapping(value = "bathupdatepaystatus")
    public WebResult bathUpdataPaystatus(@RequestBody EduOfflinePayInfo eduOfflinePayInfo) {
        Integer res = eduOfflinePayInfoService.bathUpdataPaystatus(eduOfflinePayInfo);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "修改成功", "");
        }
        return new WebResult(StatusCode.ERROR, "修改失败", "");
    }

}
