package com.yao.mybaselib.network.util;

import android.content.Context;
import android.widget.Toast;

import com.yao.mybaselib.util.NetworkUtils;
import com.yao.mybaselib.util.ToastUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yao on 2017/12/28.
 * 线程转换类
 */
public class RxSchedulers {

    /**
     * io线程-->main线程
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> io_main() {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if(!NetworkUtils.isConnected()){
//                                    Toast.makeText(context,"网络异常,哈哈",Toast.LENGTH_SHORT).show();
                                    ToastUtils.showShort("网络异常,哈哈");
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
