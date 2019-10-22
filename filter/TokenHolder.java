package com.gvt.apos.common.filter;

import com.gvt.apos.common.config.UserTokenInfo;

/**
 * @author BlueWang
 * @ClassName: TokenHolder
 * @Description:
 * @date 2019/10/11 14:11
 */
public class TokenHolder {

    private final static ThreadLocal<UserTokenInfo> requestTokenHolder = new ThreadLocal<>();

    public static void add(UserTokenInfo tokenInfo){
        requestTokenHolder.set(tokenInfo);
    }
    public static void remove(){
        requestTokenHolder.remove();
    }
    public static UserTokenInfo get(){
        return requestTokenHolder.get();
    }
}
