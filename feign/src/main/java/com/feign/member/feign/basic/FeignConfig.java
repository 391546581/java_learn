package com.feign.member.feign.basic;

import com.feign.util.DES3;
import feign.Feign;
import feign.Logger;
import feign.Response;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

/**
 * @Auther: wangcs
 * @Date: 2019/4/12 15:30
 * @Description:
 */
public interface FeignConfig {

    static Object connect(Class t) {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
//                .decoder(decoder)
//                .errorDecoder(new GitHubErrorDecoder(decoder))
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.FULL)
                .target(t, "http://192.168.101.59:8001/api");
    }
    static Object connectJava(Class t) {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
//                .decoder(decoder)
//                .errorDecoder(new GitHubErrorDecoder(decoder))
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.BASIC)
                .target(t, "http://192.168.101.56:8082");
    }

    static class WarpDecoder implements Decoder {
        private ObjectFactory<HttpMessageConverters> messageConverters;

        public WarpDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
            this.messageConverters = messageConverters;
        }
        public WarpDecoder() {
        }

        @Override
        public Object decode(Response response, Type type) throws IOException {
            Reader reader = response.body().asReader();
            if (!reader.markSupported()) {
                reader = new BufferedReader(reader, 1);
            }
            reader.mark(1);
            if (reader.read() == -1) {
                return null;
            }
            reader.reset();
            StringBuffer sb = new StringBuffer();
            char[] b = new char[1024];
            while(reader.read(b)!=-1){
                sb.append(new String(b));
            }
            try {
                return DES3.decode(sb.toString());
            } catch (Exception e) {
                throw IOException.class.cast(e.getCause());
            }
        }

    }
}
