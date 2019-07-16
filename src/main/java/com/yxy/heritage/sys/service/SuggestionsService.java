package com.yxy.heritage.sys.service;


import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.Suggestions;
import com.yxy.heritage.sys.vo.SuggestionsVo;
   /*
      意见反馈service
    */
public interface SuggestionsService{
    //提交意见反馈
    public WebResult submitSuggestions(Suggestions suggestions);
    //意见反馈列表
    public WebResult SuggestionsList(SuggestionsVo suggestionsVo);
    //删除一条意见反馈
    public WebResult deleteSuggestions(Integer id);
    //批量删除意见反馈
    public WebResult bigDeleteSuggestions(Integer [] ids);


}
