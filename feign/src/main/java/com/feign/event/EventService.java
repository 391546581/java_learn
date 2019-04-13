package com.feign.event;

import com.feign.base.ErrorType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @Auther: wangcs
 * @Date: 2019/4/13 10:46
 * @Description:
 */
public class EventService {
    private Collection listeners = new ArrayList();

    public void registerListener(ResultListener listener){
        listeners.add(listener);
    }

    public void broadCast(ResultEvent event) {
        Iterator iter = listeners.iterator();
        while (iter.hasNext()) {
            ResultListener listener = (ResultListener) iter.next();
            listener.handleEvent(event);
        }
    }



//    static EventService event;
//    static {
//        event = new EventService();
//        event.registerListener(new ResultListenerImpl());
//    }

    public static void main(String[] args) {
        EventService event = new EventService();
        event.registerListener(new ResultListenerImpl());


        event.broadCast(new ResultEvent("123", ErrorType.loginPassError));
    }
}
