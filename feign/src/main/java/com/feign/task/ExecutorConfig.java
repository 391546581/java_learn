package com.feign.task;

import com.alibaba.fastjson.JSONObject;
import com.bwoil.c2b.utils.api.dto.ResultObject;
import com.bwoil.c2b.utils.api.enums.ErrorCodeEnum;
import com.bwoil.c2b.utils.encrypt.MD5Util;
import com.bwoil.c2b.utils.json.JacksonUtil;
import com.feign.base.ErrorType;
import com.feign.base.Result;
import com.feign.member.dao.MemberDao;
import com.feign.member.dao.Members;
import com.feign.member.feign.MemberFeign;
import com.feign.member.feign.MemberInfoFeign;
import com.feign.member.feign.basic.FeignConfig;
import com.feign.member.req.LoginReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: wangcs
 * @Date: 2019/4/11 11:12
 * @Description:
 */

@Configuration
@EnableAsync
public class ExecutorConfig {

    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);

    @Bean
    static public Executor asyncServiceExecutor() {
        logger.info("start asyncServiceExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(0);
        executor.setMaxPoolSize(15);
        executor.setQueueCapacity(99999);
        executor.setKeepAliveSeconds(45);
        executor.setThreadNamePrefix("async-service-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    public static void main(String[] args) {
        MemberDao MemberDao = new MemberDao();
        MemberDao.setJdbcTemplate(JdbcTemplateUtils.getJdbcTemplate());
        List<Members> members = MemberDao.queryMembersByPage(1);


        members.forEach(x->{
        asyncServiceExecutor().execute(
            ()->{
                checkLoginJava(x);
                });
            System.out.println("exit loop..");
            });
        System.out.println("not exit..");
    }

    private static void checkLoginJava(Members x) {
        MemberInfoFeign memberInfoFeign = (MemberInfoFeign) FeignConfig.connectJava(MemberInfoFeign.class);
        Map req = new HashMap();
        req.put("mobile",x.getMobile());
        req.put("password", MD5Util.encode("a123456"));
        req.put("loginType","PWD");
        ResultObject result = memberInfoFeign.login(req);
        try {
            Double aDouble = new Double(10000 * Math.random());
            Thread.sleep(aDouble.longValue());
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getId()+ " :" + JacksonUtil.toJson(result));
        System.out.println(result.getCode());


        if(result.getCode()!= ErrorCodeEnum.SUCCESS.code()){
            Result.setResult(x.mobile, ErrorType.loginPassError);
        }
    }

    private static void checkLoginPass(Members x) {
        LoginReq req = new LoginReq();
        req.setMobile(x.getMobile());
        req.setPassword(MD5Util.encode("123456"));
        req.setLoginType("PWD");

        MemberFeign memberFeign1 = (MemberFeign) FeignConfig.connect(MemberFeign.class);
        JSONObject signin = memberFeign1.signin(req);
        System.out.println(JacksonUtil.toJson(signin));
        System.out.println(signin.getString("status"));
    }
}
