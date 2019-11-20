package com.annotation.learn;

import java.lang.annotation.*;

/**
 * @author BlueWang
 * @ClassName: Mark
 * @Description:
 * @date 2019/11/20 9:56
 */

@Target({ ElementType.TYPE, ElementType.METHOD }) //可以用在方法或者类上面
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Mark {
    String[] tags() default { "all" };
}
