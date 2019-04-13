package com.feign.member.feign.test;

import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Auther: wangcs
 * @Date: 2019/4/11 17:21
 * @Description:
 */
//192.168.101.30
//http://192.168.101.23:8086/#/home/project/inside/api/detail?apiID=32&projectID=3

@FeignClient(name = "xxx", url = "http://192.168.101.23:8086", configuration = FormFeign.FormSupportConfig.class)
public interface FormFeign {
    @RequestMapping(value = "/user/login",method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    Object post(@RequestParam Map<String, ?> queryParam);

    @RequestMapping(value = "/#/home/project/inside/api/detail",method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    Object detail(@RequestParam Map<String, ?> queryParam);


    class FormSupportConfig {
        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;
        // new一个form编码器，实现支持form表单提交
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringEncoder(messageConverters);
        }
        @Bean
        public Decoder feignFormDecoder() {
            return new SpringDecoder(messageConverters);
//            return new WarpDecoder(messageConverters);
        }
        // 开启Feign的日志
        @Bean
        public Logger.Level logger() {
            return Logger.Level.FULL;
        }
    }


}
