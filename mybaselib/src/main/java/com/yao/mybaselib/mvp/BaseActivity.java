package com.yao.mybaselib.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yao.mybaselib.R;
import com.yao.mybaselib.entity.MessageEvent;
import com.yao.mybaselib.util.ActivityStackManager;
import com.yao.mybaselib.util.EventBusUtils;
import com.yao.mybaselib.view.TitleBar;
import com.yao.mybaselib.view.statusbar.StatusBarUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

/**
 * Created by yao on 2017/5/6.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{

    protected final String TAG = this.getClass().getName();
    protected Context mContext;
    protected P mPresenter;
    protected TitleBar mTitleBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackManager.getInstance().addActivity(new WeakReference<Activity>(this));
        // 设置透明状态栏
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary),0);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);

        mPresenter = initPresenter();
        if (!EventBusUtils.isRegister(this)) {
            EventBusUtils.register(this);
        }
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            getBundleExtras(bundle);
        }
        mContext = this;
        mTitleBar = new TitleBar(this);
        initTitleBar();
        initData();
    }

    /**---------------------------------eventbus开始--------------------------------*/
//    /**
//     * 是否注册事件分发
//     *
//     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
//     */
//    protected boolean isRegisterEventBus() {
//        return false;
//    }

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

    /**
     * 接收到分发到事件
     * @param event 事件
     */
    protected void receiveEvent(MessageEvent event) {}

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
    protected void onDestroy() {
        ActivityStackManager.getInstance().removeActivity(new WeakReference<Activity>(this));
        // 注销eventbus
        if (EventBusUtils.isRegister(this)) {
            EventBusUtils.unregister(this);
        }
        // 把view从presenter中解绑
        if (mPresenter != null) {
            mPresenter.detach();
            mPresenter = null;
        }
        super.onDestroy();
    }

    /**设置布局文件*/
    protected abstract int getLayoutRes();

    /**从intent中得到bundle*/
    protected abstract void getBundleExtras(Bundle bundle);

    /**在子类中初始化presenter*/
    protected abstract P initPresenter();

    /**初始化titleBar*/
    protected abstract void initTitleBar();

    /**初始化控件后，初始化业务逻辑*/
    protected abstract void initData();
}
