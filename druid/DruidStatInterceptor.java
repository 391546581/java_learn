//package com.gvt.apos.common.druid;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
///**
// * @author BlueWang
// * @ClassName: DruidStatInterceptor
// * @Description:
// * @date 2019/10/21 9:57
// */
//
//@Component
//@Aspect
//@Slf4j
//public class DruidStatInterceptor {
//
//    public static final String POINT = "execution (* com.gvt.apos.**.service.*)";
//    @Pointcut("execution(* com.gvt.apos.*.service.*(..)) && @annotation(com.gvt.apos.common.druid.DruidAnnotation)" )
//    public void addAdvice(){
//
//    }
//
//    @Around("addAdvice()")
//    public Object Interceptor(ProceedingJoinPoint pjp){
//        Object resultData = null;
//        try {
//            String apiName = pjp.toShortString();
//            Object[] args = pjp.getArgs();
//            log.warn("******************{} start,args:{}", apiName, args);
//            resultData = pjp.proceed(pjp.getArgs());
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return resultData;
//    }
//}
