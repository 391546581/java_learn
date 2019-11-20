package com.annotation.learn;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author BlueWang
 * @ClassName: MarkHandler
 * @Description:
 * @date 2019/11/20 9:57
 */

@Component
public class MarkHandler implements ApplicationContextAware, InitializingBean {
    private ApplicationContext applicationContext;

    private List<String> allMark = new ArrayList<>();
    
    @Override
    public void afterPropertiesSet() throws Exception {
        scanMarkClass();
        scanMarkMethod();
        System.out.println(allMark);
    }

    /**
     * 查找 用 Mark 注解的 方法
     */
    private void scanMarkMethod() throws Exception{
        final Map<String, Object> permissionMap = applicationContext.getBeansWithAnnotation(Mark.class);
        System.out.println("this is permissionMap" + permissionMap.toString());
        for (final Object permissionObject : permissionMap.values()) {
            final Class<? extends Object> permissionClass = permissionObject.getClass();
            final Mark annotation = permissionClass.getAnnotation(Mark.class);
            if(annotation != null) {
                allMark.addAll(Arrays.asList(annotation.tags()));
            }
        }
    }

    private void scanMarkClass() throws Exception{
        final Map<String, Object> controllerMap = applicationContext.getBeansWithAnnotation(Mark.class);
        for (final Object controllerObject : controllerMap.values()) {
            final Class<? extends Object> controllerClass = controllerObject.getClass();
            for (Method method : controllerClass.getDeclaredMethods()) {
                Mark Mark = method.getAnnotation(Mark.class);
                if (Mark != null) {
                    allMark.addAll(Arrays.asList(Mark.tags()));
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
