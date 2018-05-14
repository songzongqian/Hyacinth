package com.yao.mybaselib.view;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yao.mybaselib.R;
import com.yao.mybaselib.util.StringUtils;
import com.yao.mybaselib.view.statusbar.StatusBarUtil;

/**
 * Created by yao on 2018/3/16.
 * 通用titlebar
 */
public class TitleBar {

    private RelativeLayout rl_title_bar;
    private LinearLayout ll_title_left, ll_title_right;
    private ImageView iv_title_left, iv_title_right;
    private TextView tv_title_left, tv_title_main, tv_title_sub, tv_title_right;
    private View v_line;
    private Activity activity;

    public TitleBar(Activity context) {
        initView(context);
    }

    private void initView(Activity context) {
        activity = context;
        rl_title_bar = context.findViewById(R.id.rl_title_bar);
        ll_title_left = context.findViewById(R.id.ll_title_left);
        ll_title_right = context.findViewById(R.id.ll_title_right);
        tv_title_left = context.findViewById(R.id.tv_title_left);
        tv_title_main = context.findViewById(R.id.tv_title_main);
        tv_title_sub = context.findViewById(R.id.tv_title_sub);
        tv_title_right = context.findViewById(R.id.tv_title_right);
        iv_title_left = context.findViewById(R.id.iv_title_left);
        iv_title_right = context.findViewById(R.id.iv_title_right);
        v_line = context.findViewById(R.id.v_line);
    }

    /**
     * 设置title主标题
     * @param titleMain 内容
     */
    public TitleBar setTitleMain(String titleMain) {
        tv_title_main.setVisibility(StringUtils.isEmpty(titleMain) ? View.GONE : View.VISIBLE);
        tv_title_main.setText(titleMain);
        return this;
    }

    /**
     * 设置title子标题
     * @param titleSub 子标题内容
     * @return TitleBar
     */
    public TitleBar setTitleSub(String titleSub) {
        tv_title_sub.setVisibility(StringUtils.isEmpty(titleSub) ? View.GONE : View.VISIBLE);
        tv_title_sub.setText(titleSub);
        return this;
    }

    /**
     * 设置左边标题字
     * @param titleLeft 左边内容
     * @return TitleBar
     */
    public TitleBar setTitleLeft(String titleLeft) {
        tv_title_left.setVisibility(StringUtils.isEmpty(titleLeft) ? View.GONE : View.VISIBLE);
        tv_title_left.setText(titleLeft);
        return this;
    }

    /**
     * 设置右边字内容
     * @param titleRight 右边内容
     * @return TitleBar
     */
    public TitleBar setTitleRight(String titleRight) {
//        tv_title_right.setVisibility(StringUtils.isEmpty(titleRight) ? View.GONE : View.VISIBLE);
        if (StringUtils.isEmpty(titleRight)) {
            tv_title_right.setVisibility(View.GONE);
//            ll_title_right.setVisibility(View.GONE);
        } else {
//            ll_title_right.setVisibility(View.VISIBLE);
            tv_title_right.setVisibility(View.VISIBLE);
            iv_title_right.setVisibility(View.GONE);
        }
        tv_title_right.setText(titleRight);
        return this;
    }

    /**
     * 设置title左边图标
     * @param resId 资源文件
     * @return TitleBar
     */
    public TitleBar setTitleIconLeft(int resId) {
        iv_title_left.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        iv_title_left.setImageResource(resId);
        return this;
    }

    /**
     * 设置title右边图标
     * @param resId 资源文件
     * @return TitleBar
     */
    public TitleBar setTitleIconRight(int resId) {
        iv_title_right.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        tv_title_right.setVisibility(resId > 0 ? View.GONE : View.VISIBLE);
        iv_title_right.setImageResource(resId);
        return this;
    }

    /**
     * 点击title左边图标的监听事件
     * @param listener 监听
     * @return TitleBar
     */
    public TitleBar setOnClickTitleLeftListener(View.OnClickListener listener) {
        if (iv_title_left.getVisibility() == View.VISIBLE) {
            if (listener == null) {
                ll_title_left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        activity.finish();
                    }
                });
            } else {
                ll_title_left.setOnClickListener(listener);
            }
        }
        return this;
    }

    /**
     * 点击title右边的监听事件
     * @param listener 监听
     * @return TitleBar
     */
    public TitleBar setOnClickTitleRightListener(View.OnClickListener listener) {
        if ((iv_title_right.getVisibility() == View.VISIBLE || tv_title_right.getVisibility() == View.VISIBLE)
                && listener != null) {
            ll_title_right.setVisibility(View.VISIBLE);
            ll_title_right.setOnClickListener(listener);
        } else {
            ll_title_right.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 设置title字体颜色
     * @param color 颜色资源
     * @return TitleBar
     */
    public TitleBar setTitleFontColor(int color) {
        tv_title_left.setTextColor(activity.getResources().getColor(color));
        tv_title_main.setTextColor(activity.getResources().getColor(color));
        tv_title_sub.setTextColor(activity.getResources().getColor(color));
        tv_title_right.setTextColor(activity.getResources().getColor(color));
        return this;
    }

    /**
     * 设置title的背景颜色
     * @param color 色值
     * @return TitleBar
     */
    public TitleBar setTitleBackgroudColor(int color) {
        rl_title_bar.setBackgroundColor(activity.getResources().getColor(color));
        StatusBarUtil.setColor(activity, activity.getResources().getColor(color), 0);
        return this;
    }

    /**
     * 设置title下方的线是否显示
     * @param isVisible 是否显示
     * @return TitleBar
     */
    public TitleBar setTitleLineVisibility(boolean isVisible) {
        v_line.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        return this;
    }
}
