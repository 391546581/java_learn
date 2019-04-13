package com.feign.event;

import java.util.EventListener;

/**
 * @Auther: wangcs
 * @Date: 2019/4/13 10:41
 * @Description:
 */
public interface ResultListener extends EventListener {
    void handleEvent(ResultEvent event);
}
