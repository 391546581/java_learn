//package com.gvt.apos.common.config;
//
//import com.gvt.apos.common.filter.TokenHolder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author BlueWang
// * @ClassName: FeignInterceptor
// * @Description:
// * @date 2019/10/11 11:40
// */
//
////@Configuration
////public class WebConfiguraction implements WebMvcConfigurer {
////    @Override
////    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(new FeignInterceptor()).addPathPatterns("/api/*");
////    }
////}
//
//
//@Slf4j
//public class FeignInterceptor extends HandlerInterceptorAdapter {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
//        final String header = request.getHeader("Accept-Language");
//        System.out.println(header);
//        request.getHeader("Accept-Language");
////        LangHolder.add(header);
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
//        TokenHolder.remove();
//        return;
//    }
//}
