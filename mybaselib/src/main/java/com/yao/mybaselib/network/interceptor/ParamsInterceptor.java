package com.yao.mybaselib.network.interceptor;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yao on 2018/1/9.
 * 公共参数拦截器
 */

public class ParamsInterceptor implements Interceptor {

    private Map<String, String> params;

    public ParamsInterceptor(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        if (params != null) {
            Request.Builder newBuilder = oldRequest.newBuilder();

            //GET请求则使用HttpUrl.Builder来构建
            if ("GET".equalsIgnoreCase(oldRequest.method())) {

                HttpUrl.Builder httpUrlBuilder = oldRequest.url().newBuilder();
                for (String key : params.keySet()) {
                    httpUrlBuilder.addQueryParameter(key, params.get(key));
                }
                newBuilder.url(httpUrlBuilder.build());

            } else {
                // 如果原请求是表单请求
                if (oldRequest.body() instanceof FormBody) {

                    FormBody.Builder formBodyBuilder = new FormBody.Builder();
                    for (String key : params.keySet()) {
                        formBodyBuilder.add(key, params.get(key));
                    }
                    newBuilder.post(formBodyBuilder.build());
                }

                //TODO  处理其他类型的request.body
            }
            return chain.proceed(newBuilder.build());
        }
        return chain.proceed(oldRequest);
    }
}
