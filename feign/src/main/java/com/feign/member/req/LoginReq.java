package com.feign.member.req;

import lombok.Data;

/**
 * @Auther; // wangcs
 * @Date; // 2019/4/10 10; //25
 * @Description; //
 */
@Data
public class LoginReq {
    private String loginType; // pwd;
    private String mobile; // string;
    private String password; // string;
    private String imageCode; // string;
    private String countryCode; // string;
    private String vcode; // string;
    private String pcode; // string;
    private String source; // pc;
    private String marketSource; // string;
    private String token="123"; // string;
    private String boVer="1.0"; // string;
}
