package com.yao.mybaselib.util;

import com.yao.mybaselib.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yao on 2017/12/27.
 * 将EventBus封装一层.
 */
public class EventBusUtils {

    public static boolean isRegister(Object subscriber) {
        return EventBus.getDefault().isRegistered(subscriber);
    }

    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(MessageEvent event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(MessageEvent event) {
        EventBus.getDefault().postSticky(event);
    }

    // 其他
}
