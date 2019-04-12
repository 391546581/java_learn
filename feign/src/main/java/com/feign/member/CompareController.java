package com.feign.member;

import com.bwoil.c2b.utils.api.dto.ListResultObject;
import com.bwoil.c2b.utils.api.dto.ResultObject;
import com.bwoil.c2b.utils.encrypt.MD5Util;
import com.bwoil.c2b.utils.json.JacksonUtil;
import com.feign.member.dao.MemberDao;
import com.feign.member.dao.Members;
import com.feign.member.feign.MemberFeign;
import com.feign.member.feign.MemberInfoFeign;
import com.feign.member.feign.old.PHPFeign;
import com.feign.member.req.LoginReq;
import com.feign.member.rsp.MemberEntity;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: wangcs
 * @Date: 2019/4/10 10:27
 * @Description:
 */

@RestController
@RequestMapping("/api")
public class CompareController {

    @Autowired
    MemberFeign memberFeign;

    @Autowired
    PHPFeign phpFeign;
    @Autowired
    MemberInfoFeign memberInfoFeign;
    @Autowired
    MemberDao memberDao;

    @Autowired
    FormFeign formFeign;


    @GetMapping(value="/members")
    public String getMembers(){

        Map req = new HashMap();
        req.put("mobile","15012478873");
        req.put("password",MD5Util.encode("a123456"));
        req.put("loginType","PWD");
        req.put("token","1234");

        PHPFeign php = PHPFeign.connect();
        Object signin = php.login2(JacksonUtil.toJson(req));

//        Object signin = phpFeign.login(JacksonUtil.toJson(req));

        System.out.println(JacksonUtil.toJson(signin));
        return JacksonUtil.toJson(signin);
    }

    @GetMapping(value="/membersNewListAndLogin")
    public List<Members> getMembersNew(@RequestParam int page){

        List<Members> members = memberDao.queryMembersByPage(page);
        System.out.println(JacksonUtil.toJson(members));

        members.forEach(x->{
            LoginReq req = new LoginReq();
            req.setMobile(x.getMobile());
            req.setPassword(MD5Util.encode("123456"));
            req.setLoginType("PWD");

            Object signin = memberFeign.signin(req);

            System.out.println(JacksonUtil.toJson(signin));
        });
        return members;
    }


    //ResultObject<ListResultObject<MemberEntity>> result = getListByFeign(page);
    private ResultObject<ListResultObject<MemberEntity>> getListByFeign(@RequestParam int page) {
        Map queryMap= new HashMap<>();

        Map bodyMap= new HashMap<>();
        queryMap.put("page",page);
        queryMap.put("limit",10);
        queryMap.put("sort"," member_id.asc ");

        return memberInfoFeign.queryMemberByAdm(queryMap,bodyMap);
    }


    @GetMapping("/user")
    public Object findById() {
        HashMap<String, String> param = Maps.newHashMap();
        param.put("account","15012478873");
        param.put("password",MD5Util.encode("a123456"));
        param.put("loginType","pwd");
        param.put("devinfo","");

        return formFeign.post(param);
    }

    @GetMapping("/detail")
    public Object detail() {
        HashMap<String, String> param = Maps.newHashMap();
        param.put("account","13923811759");
        param.put("password","a123456");
        param.put("loginType","pwd");
        param.put("devinfo","");

        return formFeign.detail(param);
    }

}
