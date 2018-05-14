package com.yao.mybaselib.network.util;

import android.util.Log;

import com.yao.mybaselib.network.interceptor.HeaderInterceptor;
import com.yao.mybaselib.network.interceptor.ParamsInterceptor;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by yao on 2018/1/3.
 */

public class InterceptorUtils {
    public static String TAG="----";
    /**
     * 日志拦截器
     */
    public static HttpLoggingInterceptor LogInterceptor(){
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.w(TAG, "log: "+message );
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }

    /**
     * 请求头拦截器
     */
    public static Interceptor HeaderInterceptor(){
        return new HeaderInterceptor(getHeaderMap());
    }

    /**
     * 公共参数拦截器
     */
    public static Interceptor ParamsInterceptor() {
        return new ParamsInterceptor(getParamsMap());
    }

    private static Map<String, String> getHeaderMap(){
        Map<String, String> map = new HashMap<>();
        map.put("version", "1.0");
        return map;
    }

    private static Map<String, String> getParamsMap(){
        Map<String, String> map = new HashMap<>();
        map.put("version", "1.0");
        map.put("aid", "android");
        map.put("imei", "1234560");
        return map;
    }
}
