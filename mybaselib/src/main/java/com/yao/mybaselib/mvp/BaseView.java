package com.yao.mybaselib.mvp;

/**
 * Created by yao on 2018/1/7.
 */

public interface BaseView {

    void showLoadingDialog();

    void dismissLoadingDialog();

    void showLoadingView();

    void showNoNetworkView();

    void showErrorView();

    void showEmptyView();

    void showContentView();
}
