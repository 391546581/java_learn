package com.feign.base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wangcs
 * @Date: 2019/4/13 11:18
 * @Description:
 */
public class Result {
    public static Map<String,List<ErrorType>> mapResult = new HashMap<>();

    public static void setResult(String key,ErrorType error){
        if(mapResult.containsKey(key)){
            mapResult.get(key).add(error);
        }else{
            mapResult.put(key, Arrays.asList(error));
        }
    }
}
