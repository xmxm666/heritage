package com.yxy.heritage.sys.dao;

import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.Suggestions;
import com.yxy.heritage.sys.vo.SuggestionsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 意见反馈mapper
 */
@Mapper
public interface SuggestionsMapper {

    //提交意见反馈
    public void submitSuggestions(Suggestions suggestions);
    //意见反馈列表
    public List<Suggestions> SuggestionsList(SuggestionsVo suggestionsVo);
    //删除一条意见反馈
    public void deleteSuggestions(Integer id);
    //批量删除意见反馈
    public void bigDeleteSuggestions(Integer [] ids);
}
