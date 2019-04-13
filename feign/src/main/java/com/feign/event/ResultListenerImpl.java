package com.feign.event;

/**
 * @Auther: wangcs
 * @Date: 2019/4/13 10:58
 * @Description:
 */
public class ResultListenerImpl implements  ResultListener {
    @Override
    public void handleEvent(ResultEvent event) {
        System.out.println("handle : "+event.error);
    }
}
