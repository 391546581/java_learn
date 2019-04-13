package com.feign.event;

import com.feign.base.ErrorType;

import java.util.EventObject;

/**
 * @Auther: wangcs
 * @Date: 2019/4/13 10:38
 * @Description:
 */
public class ResultEvent extends EventObject {
    String key;
    ErrorType error;

    public ResultEvent(String key, ErrorType error) {
        super(error);
        this.key = key;
        this.error = error;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ResultEvent(Object source) {
        super(source);
    }
}
