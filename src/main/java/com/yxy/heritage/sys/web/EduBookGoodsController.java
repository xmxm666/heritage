package com.yxy.heritage.sys.web;


import com.yxy.heritage.http.result.StatusCode;
import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.EduBookGoods;
import com.yxy.heritage.sys.service.EduBookGoodsService;
import com.yxy.heritage.sys.vo.PageBean;
import com.yxy.heritage.sys.vo.QueryBookVos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author vvtxw
 * @create 2019-05-16 11:47
 */
@RestController
@RequestMapping("booksell")
public class EduBookGoodsController {

    @Resource
    private EduBookGoodsService eduBookGoodsService;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 分页查询所有教材
     *
     * @return
     */
    @GetMapping(value = "bookshow")
    public WebResult queryAllshowBooks(@RequestParam(value = "catId") Integer catId, @RequestParam(value = "extendCatId") Integer extendCatId) {
        QueryBookVos queryBookVos = eduBookGoodsService.queryAllBookesForPage(catId, extendCatId);

        return new WebResult(StatusCode.OK, "查询成功", queryBookVos);
    }

    /**
     * 搜索功能
     *
     * @param searchkey
     * @return
     */
    @GetMapping(value = "search")
    public WebResult querySearch(String searchkey) {

        if (searchkey == null || searchkey == "") {
            return new WebResult(StatusCode.OK, "查询为空", "");
        }
        PageBean pageBean = eduBookGoodsService.queryAllBookesForlike(searchkey);
        return new WebResult(StatusCode.OK, "查询成功", pageBean);
    }


    /**
     * 添加新教材
     *
     * @param eduBookGoods
     * @return
     */
    @PostMapping(value = "server/addbooks")
    public WebResult addBooks(@RequestBody EduBookGoods eduBookGoods) {
        Integer res = eduBookGoodsService.addBooks(eduBookGoods);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "添加成功", "");
        }
        return new WebResult(StatusCode.OK, "添加失败", "");
    }

    /**
     * 查询1条信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "server/showone")
    public WebResult queryById(Integer id) {
        EduBookGoods eduBookGoods = eduBookGoodsService.queryById(id);
        return new WebResult(StatusCode.OK, "查询成功", eduBookGoods);
    }

    /**
     * 分页显示教材列表
     *
     * @param page
     * @param size
     * @param schoolId
     * @return
     */
    @GetMapping(value = "server/shows")
    public WebResult showPage(@RequestParam("pageNum") Integer page, @RequestParam("pageSize") Integer size, String schoolId, String booksAuthor, String booksSn, Byte isOnSale) {
        PageBean pageBean = eduBookGoodsService.showPageBooks(page, size, schoolId, booksAuthor, booksSn, isOnSale);
        return new WebResult(StatusCode.OK, "查询成功", pageBean);
    }


    /**
     * 修改教材
     *
     * @param eduBookGoods
     * @return
     */
    @PostMapping(value = "server/updateco")
    public WebResult updateCodition(@RequestBody EduBookGoods eduBookGoods) {
        int result = eduBookGoodsService.updateBook(eduBookGoods);
        if (result > 0) {
            return new WebResult(StatusCode.OK, "修改设置成功", "");
        }
        return new WebResult(StatusCode.ERROR, "修改设置失败", "");
    }

    /**
     * 删材
     *
     * @param eduBookGoods
     * @return
     */
    @PostMapping(value = "server/deleteco")
    public WebResult deleteCodition(@RequestBody EduBookGoods eduBookGoods) {
        int result = eduBookGoodsService.deleteBook(eduBookGoods);
        if (result > 0) {
            return new WebResult(StatusCode.OK, "删除设置成功", "");
        }
        return new WebResult(StatusCode.ERROR, "删除设置失败", "");
    }

    /**
     * 上架和下架
     *
     * @param eduBookGoods
     * @return
     */
    @PostMapping(value = "server/UpdataIsOnSale")
    public WebResult upGoodsBook(@RequestBody EduBookGoods eduBookGoods) {
        Integer res = eduBookGoodsService.UpdataIsOnSale(eduBookGoods);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "修改成功", "");
        }
        return new WebResult(StatusCode.ERROR, "修改失败", "");
    }

    /**
     * 批量上架和下架
     *
     * @param eduBookGoods
     * @return
     */
    @PostMapping(value = "server/bathUpdataIsOnSale")
    public WebResult bathUpdataPaystatus(@RequestBody EduBookGoods eduBookGoods) {
        Integer res = eduBookGoodsService.bathUpdataIsOnSale(eduBookGoods);
        if (res > 0) {
            return new WebResult(StatusCode.OK, "修改成功", "");
        }
        return new WebResult(StatusCode.ERROR, "修改失败", "");
    }


}
