package com.yxy.heritage.sys.service;
import com.yxy.heritage.sys.bean.EduBookGoods;
import com.yxy.heritage.sys.vo.PageBean;
import com.yxy.heritage.sys.vo.QueryBookVos;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author vvtxw
 * @create 2019-05-16 11:57
 */
public interface EduBookGoodsService {
    //查询所有教材
    QueryBookVos queryAllBookesForPage(Integer catId, Integer extendCatId);

    //搜索分页展示
    PageBean queryAllBookesForlike(String searchkey);

    //后台添加书籍教材
    Integer addBooks(EduBookGoods eduBookGoods);

    //查询书籍教材
    EduBookGoods queryById(Integer id);

    //更新书籍信息
    int updateBook(EduBookGoods eduBookGoods);

    //删除书籍教材
    int deleteBook(EduBookGoods eduBookGoods);

    //分页展示教材
    PageBean showPageBooks(Integer page, Integer size, String schoolId, String booksAuthor, String booksSn, Byte isOnSale);

    //上架和下架
    Integer UpdataIsOnSale(EduBookGoods eduBookGoods);


    //批量上架和下架
    Integer bathUpdataIsOnSale(@RequestBody EduBookGoods eduBookGoods);
}
