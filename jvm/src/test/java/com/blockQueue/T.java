package com.blockQueue;

import com.alibaba.fastjson.JSONObject;

/**
 * @Auther: wangcs
 * @Date: 2019/4/9 14:06
 * @Description:
 */
public class T {

    public static void main(String[] args) {
        final Object[] items = new Object[5];
        items[1] = 1;
        System.out.println(JSONObject.toJSONString(items));
        items[4] = 5;
        System.out.println(JSONObject.toJSONString(items));
    }
}
