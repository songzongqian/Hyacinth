package com.yao.mybaselib.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yao.mybaselib.entity.MessageEvent;
import com.yao.mybaselib.util.EventBusUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by yao on 2018/1/7.
 */

public abstract class BaseFragment<P extends BasePresenter> extends CommonFragment implements BaseView{

    protected View rootView;
    protected Context mContext;
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(), container, false);
        }
        ButterKnife.bind(this, rootView);

        if (!EventBusUtils.isRegister(this)) {
            EventBusUtils.register(this);
        }

        if (null != getArguments()) {
            getBundleExtras(getArguments());
        }
        mContext = getActivity();
        mPresenter = initPresenter();
        initData();
        return rootView;
    }

    /**---------------------------------eventbus开始--------------------------------*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventCome(MessageEvent event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventCome(MessageEvent event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    /**---------------------------------dialog开始--------------------------------*/

    @Override
    public void showLoadingDialog() {}

    @Override
    public void dismissLoadingDialog() {}

    @Override
    public void showLoadingView() {}

    @Override
    public void showContentView() {}

    @Override
    public void showEmptyView() {}

    @Override
    public void showErrorView() {}

    @Override
    public void showNoNetworkView() {}

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 注销eventbus
        if (EventBusUtils.isRegister(this)) {
            EventBusUtils.unregister(this);
        }
        // 把view从presenter中解绑
        if (mPresenter != null) {
            mPresenter.detach();
            mPresenter = null;
        }
    }

    /**设置布局文件*/
    protected abstract int getLayoutRes();

    /**从arguments中得到bundle*/
    protected abstract void getBundleExtras(Bundle arguments);

    /**在子类中初始化presenter*/
    protected abstract P initPresenter();

    /**初始化控件后，初始化业务逻辑*/
    protected abstract void initData();

    /**
     * 接收到分发到事件
     * @param event 事件
     */
    protected void receiveEvent(MessageEvent event) {}


}
