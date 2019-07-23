package com.yxy.heritage.sys.vo;


import com.yxy.heritage.sys.bean.EduComment;

/**
    评论的
 */
public class CommentVo extends EduComment {

    private String userName;

    private String image;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
