package com.feign.member.feign;

import com.bwoil.c2b.utils.api.dto.ListResultObject;
import com.bwoil.c2b.utils.api.dto.ResultObject;
import com.feign.member.rsp.MemberEntity;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@FeignClient(name = "BwoilMember",url="http://127.0.0.1:8082")
@Headers({"content-type: application/json"})
public interface MemberInfoFeign {

    @RequestMapping(path="/api/member/queryMemberByAdm", method = RequestMethod.POST)
    @RequestLine("POST /api/member/queryMemberByAdm")
    ResultObject<ListResultObject<MemberEntity>> queryMemberByAdm(Map req, @RequestParam @Param("map") Map map);

//    @RequestMapping(path="/api/member/login",headers = {"boVer=1.0","boPlat=APP" ,"boDes=0"}, method = RequestMethod.POST)
    //@RequestHeader("token") String token
    @RequestLine("POST /api/member/login")
    ResultObject<?> login(Map req);

}
