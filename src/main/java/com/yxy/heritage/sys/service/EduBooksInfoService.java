package com.yxy.heritage.sys.service;

import com.yxy.heritage.sys.bean.EduBooksInfo;
import com.yxy.heritage.sys.vo.EduBooksInfoVo;
import com.yxy.heritage.sys.vo.PageBean;

import java.util.List;

/**
 * @author vvtxw
 * @create 2019-05-17 11:41
 */
public interface EduBooksInfoService {

    //查询详情
    EduBooksInfoVo selectBookById(Integer id);

    // 添加教材详情
    Integer addBookInfo(EduBooksInfo eduBooksInfo);

    //删除教材详情
    Integer deleteBookInfo(EduBooksInfo eduBooksInfo);

    //修改教材详情
    Integer updateBookInfo(EduBooksInfo eduBooksInfo);

    //查询一条教材详情
    EduBooksInfo selectoneBookInfo(Integer id);

    //分页查询教材详情
    PageBean showPageBookInfo(Integer page, Integer size, String schoolId, Integer id);

    //根据教材id查询书名
    List<String> queryBookNameById(Integer id);
}
