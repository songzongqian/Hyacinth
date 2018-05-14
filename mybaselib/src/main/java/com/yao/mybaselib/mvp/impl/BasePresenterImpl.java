package com.yao.mybaselib.mvp.impl;

import com.yao.mybaselib.mvp.BasePresenter;
import com.yao.mybaselib.mvp.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by yao on 2018/1/7.
 * basepresenter的实现类
 */
public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter {

    protected V view;
    //将所有正在处理的Subscription都添加到CompositeSubscription中。统一退出的时候注销观察
    private CompositeDisposable mCompositeDisposable;

    public BasePresenterImpl(V view) {
        this.view = view;
        start();
    }

    @Override
    public void start() {

    }

    @Override
    public void detach() {
        this.view = null;
        unDisposable();
    }

    @Override
    public void addDisposable(Disposable subscription) {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    @Override
    public void unDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
