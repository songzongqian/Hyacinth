package com.yao.mybaselib.network.observer;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by yao on 2018/1/3.
 */

public abstract class JsonObserver<T> extends BaseObserver<T> implements Observer<T> {

    private Disposable disposable;

    public JsonObserver(Context context) {
        this.context = context;
    }
    public JsonObserver(Context context, boolean isShowDialog) {
        this.context = context;
        this.isShowDialog = isShowDialog;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (isShowDialog) {
            showDialog();
        }
        disposable = d;
        onStart();
    }

    @Override
    public void onNext(T value) {
        if (value == null) return;

        onSuccess(value);

    }

    @Override
    public void onComplete() {
        if (isShowDialog) {
            dismissDialog();
        }
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        if (isShowDialog) {
            dismissDialog();
        }
        parserExceptionType(e);
    }

    @Override
    protected void onCancleDialog() {
        super.onCancleDialog();
        disposable.dispose();
    }
}
