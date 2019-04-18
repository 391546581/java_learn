package com.feign.member.feign;

import com.alibaba.fastjson.JSONObject;
import com.feign.member.feign.basic.FeignConfig;
import com.feign.member.req.LoginReq;
import feign.Headers;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.cloud.netflix.feign.FeignClient;


/**
 * @Auther: wangcs
 * @Date: 2019/4/10 10:13
 * @Description:
 */

@FeignClient(name = "BWOIL-SWOFT-API",url="http://192.168.101.59:8001/api" ,configuration = FeignConfig.class)
@Headers({"boVer:1.0","boPlat:APP" ,"boDes:0","content-type: application/json"})
public interface MemberFeign{

    @RequestMapping(path="/member/account/signin",headers = {"boVer=1.0","boPlat=APP" ,"boDes=0"}, method = RequestMethod.POST)
    @RequestLine("POST /member/account/signin")
    JSONObject signin(@RequestBody LoginReq req);


}
