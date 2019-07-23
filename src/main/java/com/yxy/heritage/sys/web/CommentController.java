package com.yxy.heritage.sys.web;

import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.EduComment;
import com.yxy.heritage.sys.service.CommentService;
import com.yxy.heritage.sys.vo.PageComVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 评论
 */
@RestController
@RequestMapping("/her/comment")
public class CommentController {

   @Autowired
    private CommentService commentService;

  /**
   *     评论
   * @param comment
   * @return
   */
  @PostMapping(value = "/addComment")
   public WebResult addComment(HttpServletRequest request, @RequestBody EduComment comment){
      String userId = request.getHeader("userId");
      if(StringUtils.isBlank(userId) || userId.equals("null")) {
          return new WebResult("400", "请登录！", "");
      }
      comment.setUid(Integer.valueOf(userId));
       return  commentService.addComment(comment);
   }

    /**
     *  评论列表
     * @param request
     * @param pageComVo
     * @return
     */
   @PostMapping(value = "/commentList")
   public WebResult commentList(HttpServletRequest request, @RequestBody PageComVo pageComVo) {
       return commentService.commentList(pageComVo);
   }

    /**
     * 删除评论
     * @param comid
     * @return
     */
    @GetMapping(value = "/deleteComment")
    public WebResult deleteComment(@RequestParam(value = "comid") Integer comid) {
        return commentService.deleteComment(comid);
    }
}
