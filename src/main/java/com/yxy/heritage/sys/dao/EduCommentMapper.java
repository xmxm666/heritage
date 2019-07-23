package com.yxy.heritage.sys.dao;

import com.yxy.heritage.sys.bean.EduComment;
import com.yxy.heritage.sys.vo.CommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EduCommentMapper {

    int deleteByPrimaryKey(Integer comid);

    int insert(EduComment record);

    List<CommentVo> selectList(@Param("uid") Integer uid, @Param("newtype") Integer newtype, @Param("newsid") Integer newsid);

    EduComment selectByPrimaryKey(Integer comid);

    int updateByPrimaryKeySelective(EduComment record);

}