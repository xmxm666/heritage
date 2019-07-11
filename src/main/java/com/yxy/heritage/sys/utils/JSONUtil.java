package com.yxy.heritage.sys.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by caofanCPU on 2018/8/6.
 */
public class JSONUtil {
    
    public static List<Map<String, Object>> parseList(Object source) {
        List<Map> tempList = new ArrayList();
        if (source instanceof ArrayList) {
            tempList = (ArrayList) source;
        }
        final List<Map<String, Object>> resultList = new ArrayList<>();
        tempList.forEach((item -> resultList.add(item)));
        return resultList;
    }
    
    public static String outputJson(String jsonStr) {
        String start = "    ";
        // 用户标记层级
        int level = 0;
        StringBuilder jsonResultStr = new StringBuilder();
        // 循环遍历每一个字符
        for (int i = 0; i < jsonStr.length(); i++) {
            // 获取当前字符
            char piece = jsonStr.charAt(i);
            // 如果上一个字符是断行，则在本行开始按照level数值添加标记符，排除第一行
            if (i != 0 && '\n' == jsonResultStr.charAt(jsonResultStr.length() - 1)) {
                for (int k = 0; k < level; k++) {
                    jsonResultStr.append(start);
                }
            }
            switch (piece) {
                case '{':
                case '[':
                    // 如果字符是{或者[，则断行，level加1
                    jsonResultStr.append(piece + "\n");
                    level++;
                    break;
                case ',':
                    // 如果是","，则断行
                    jsonResultStr.append(piece + "\n");
                    break;
                case '}':
                case ']':
                    // 如果是"}"或者"]"，则断行，level减1
                    jsonResultStr.append("\n");
                    level--;
                    for (int k = 0; k < level; k++) {
                        jsonResultStr.append(start);
                    }
                    jsonResultStr.append(piece);
                    break;
                default:
                    jsonResultStr.append(piece);
                    break;
            }
        }
        return jsonResultStr.toString().replaceAll("\n\n", "\n");
    }
    
}
