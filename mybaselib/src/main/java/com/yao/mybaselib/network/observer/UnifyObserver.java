package com.yao.mybaselib.network.observer;

import com.yao.mybaselib.entity.BaseResult;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by yao on 2017/12/28.
 * 封装个基类observer
 */
public abstract class UnifyObserver<T> extends BaseObserver<T> implements Observer<BaseResult<T>> {

    @Override
    public void onSubscribe(Disposable d) {
        onStart();
    }

    @Override
    public void onNext(BaseResult<T> value) {
        if (value.isSuccess()) {
            T t = value.getData();
            onSuccess(t);
        } else {
            onFailed(value.getCode(), value.getMessage());
        }
    }

    @Override
    public void onError(Throwable e) {
        parserExceptionType(e);
    }

    @Override
    public void onComplete() {
        onFinish();
    }
}
