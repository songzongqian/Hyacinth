package com.yao.mybaselib.mvp;

import io.reactivex.disposables.Disposable;

/**
 * Created by yao on 2018/1/7.
 */

public interface BasePresenter {

    /**
     * 默认初始化
     */
    void start();

    /**
     * activity关闭把view对象置空
     */
    void detach();

    /**
     * 将每个网络请求的每一个Disposable添加进CompositeDisposable
     * 在activity关闭或退出时一并注销
     * @param subscription 订阅者
     */
    void addDisposable(Disposable subscription);

    /**
     * 注销所有请求
     */
    void unDisposable();
}
