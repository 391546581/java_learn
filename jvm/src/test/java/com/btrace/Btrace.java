package com.btrace;

import com.sun.btrace.AnyType;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.println;

@BTrace
public class Btrace {
//    @OnMethod(
//            clazz = "com.btrace.BusiClass",
//            method = "sayHello",
//            location = @Location(Kind.RETURN)//
//    )
//    public static void sayHello(String name, int age, @Return String result) {
//       println("name: " + name);
//       println("age: " + age);
//       println(result);
////        if(BTraceUtils.matches(".*345.*", str)) {
////            println(str);
////        }
//    }

    @OnMethod(
            clazz = "com.bwoil.c2b.service.member.controller.MemberController",
            method = "queryMember",
            location = @Location(Kind.RETURN)
    )
    public static void queryMember(@Self int s1, String s2,String s3 ,@Return AnyType result) {
       println(1234);
       println(result);
    }

//    public static void sayHello(@Duration long duration) {//zhuan ms
//        println(strcat("duration(ms): ", str(duration / 1000000)));
//    }

    @OnMethod(clazz = "java.lang.Thread", method = "start")
    public static void onThreadStart() {
        println("tracing method start");
    }
}
