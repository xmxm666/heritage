package com.yxy.heritage.sys.web;

import com.yxy.heritage.constant.ConstantClassField;
import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.Admin;
import com.yxy.heritage.sys.bean.Suggestions;
import com.yxy.heritage.sys.service.SuggestionsService;
import com.yxy.heritage.sys.utils.RedisUtil;
import com.yxy.heritage.sys.vo.SuggestionsVo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *意见反馈
 * Created by looyer on 2019/4/2.
 */
  @RestController
public class SuggestionsController {
      @Autowired
    private RedisUtil redisUtil;
      @Autowired
    private SuggestionsService suggestionsService;

    /**
     * 提交意见反馈
     *
     * @param request,suggestions
     * @return
     */
    @PostMapping(value = "/her/submitSuggestions")
    public WebResult submitSuggestions(HttpServletRequest request, @RequestBody Suggestions suggestions) {

        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
          String userId=request.getHeader("userId");
               suggestions.setUserId(Integer.parseInt(userId));
          return   suggestionsService.submitSuggestions(suggestions);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }
    /**
     * 意见反馈列表
     *
     * @param request,suggestions
     * @return
     */
    @PostMapping (value = "/server/SuggestionsList")
    public WebResult SuggestionsList(HttpServletRequest request, @RequestBody SuggestionsVo suggestionsVo) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);
            Admin hget =(Admin) redisUtil.hget(ConstantClassField.ADMIN_INFO, id.toString());
            if(hget.getRole() != 0) {
                suggestionsVo.setSchoolId(Integer.valueOf(hget.getSchoolId().toString()));
            }
            return  suggestionsService.SuggestionsList(suggestionsVo);
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }
    /**
     * 删除一条意见记录
     *
     * @param request,suggestions
     * @return
     */
    @PostMapping(value = "/server/deleteSuggestions")
    public WebResult deleteSuggestions(HttpServletRequest request,@RequestBody Suggestions suggestions) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);

            return  suggestionsService.deleteSuggestions(suggestions.getId());
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }
    /**
     * 批量删除意见记录
     *
     * @param request,suggestions
     * @return
     */
    @PostMapping(value = "/server/bigDeleteSuggestions")
    public WebResult bigDeleteSuggestions(HttpServletRequest request,@RequestBody Suggestions suggestions) {
        String token = request.getHeader("token");
        Object o = redisUtil.get(token);
        if(o != null) {
            Integer id = (Integer) o;
            // 如果是pc端登录，更新token缓存失效时间
            redisUtil.expire(token, ConstantClassField.PC_CACHE_EXPIRATION_TIME);

            return  suggestionsService.bigDeleteSuggestions(suggestions.getIds());
        }else {
            return new WebResult("50012", "请先登录", "");
        }
    }


}
