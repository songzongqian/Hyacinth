package com.yao.mybaselib.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by yao on 2018/1/7.
 */

public abstract class CommonFragment extends Fragment {

    protected final String TAG = this.getClass().getName();

    private boolean isViewCreate = false;//view是否创建
    private boolean isViewVisible = false;//view是否可见
    private boolean isFirst = true;//是否第一次加载

    public Context mContext;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isViewVisible = isVisibleToUser;
        if (isVisibleToUser && isViewCreate) {
            visibleToUser();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewCreate = true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isViewVisible) {
            visibleToUser();
        }
    }

    protected void visibleToUser() {
        if (isFirst) {
            lazyLoad();
            isFirst = false;
        }
    }

    /**
     * 懒加载，第一次加载让用户可见
     */
    protected void lazyLoad() {
        Log.d("-------" + TAG, "lazyLoad!!!!!!!!!!!!!!!!11");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isViewCreate = false;
    }
}
