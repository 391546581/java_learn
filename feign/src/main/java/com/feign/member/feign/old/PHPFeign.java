package com.feign.member.feign.old;

import com.bwoil.c2b.utils.json.JacksonUtil;
import com.feign.util.DES3;
import com.google.gson.Gson;
import feign.Feign;
import feign.Logger;
import feign.RequestLine;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

@FeignClient(name = "c2b",url="http://192.168.101.30:8080")
public interface PHPFeign {

    @RequestLine("POST /user/login")
    @RequestMapping(path="/user/login",headers = {"Content-Type=application/x-www-form-urlencoded","boVer=1.0"}, method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    Object login(String req);

    class Login{
        String mobile;
        String password;
        String loginType;

        public Login(String mobile, String password, String loginType) {
            this.mobile = mobile;
            this.password = password;
            this.loginType = loginType;
        }
    }

    @RequestLine("POST /user/login")
    @RequestMapping(path="/user/login",headers = {"Content-Type=application/x-www-form-urlencoded"}, method = RequestMethod.POST)
    Object login2(String login);

//    @RequestLine("POST /user/login")
//    @RequestMapping(path="/user/login",headers = {"Content-Type=application/x-www-form-urlencoded"}, method = RequestMethod.POST)
//    Object login3(String mobile,String password);


    public static void main(String... args) {
        PHPFeign php = PHPFeign.connect();
//        String pwd = new Gson().toJson(new Login("15012478873", "a123456", "pwd"));
//        Object o = php.login3("15012478873", "a123456");
//        System.out.println(JacksonUtil.toJson(o));

        String pwd = new Gson().toJson(new Login("15012478873", "a123456", "pwd"));
        Object o = php.login2(pwd);
        System.out.println(JacksonUtil.toJson(o));
    }

    class WarpDecoder implements Decoder{
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
    class WarpNIODecoder implements Decoder{
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
            try {
                return DES3.decode(sb.toString());
            } catch (Exception e) {
                throw IOException.class.cast(e.getCause());
            }
        }

    }

    static PHPFeign connect() {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new WarpDecoder())
//                .decoder(decoder)
//                .errorDecoder(new GitHubErrorDecoder(decoder))
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.FULL)
                .target(PHPFeign.class, "http://127.0.0.1:8080");
    }

    static class GitHubErrorDecoder implements ErrorDecoder {
        final Decoder decoder;
        final ErrorDecoder defaultDecoder = new ErrorDecoder.Default();

        GitHubErrorDecoder(Decoder decoder) {
            this.decoder = decoder;
        }

        @Override
        public Exception decode(String methodKey, Response response) {
            try {
                return (Exception) decoder.decode(response, GitHubClientError.class);
            } catch (IOException fallbackToDefault) {
                return defaultDecoder.decode(methodKey, response);
            }
        }
    }

    static class GitHubClientError extends RuntimeException {
        private String message; // parsed from json

        @Override
        public String getMessage() {
            return message;
        }
    }

}
