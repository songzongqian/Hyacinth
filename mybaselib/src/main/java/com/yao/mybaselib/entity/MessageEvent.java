package com.yao.mybaselib.entity;

/**
 * Description: EventBus传递消息总体类
 * Date: 2016/11/17
 */

public class MessageEvent<T> {

    private int eventCode = -1;

    private T data;

    public MessageEvent(int eventCode) {
        this.eventCode = eventCode;
    }

    public MessageEvent(int eventCode, T data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    public int getEventCode() {
        return eventCode;
    }

    public T getData() {
        return data;
    }
}
