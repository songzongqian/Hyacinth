package com.yao.mybaselib.network.observer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ParseException;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.yao.mybaselib.network.Const_http;
import com.yao.mybaselib.util.ToastUtils;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * Created by yao on 2018/1/3.
 */

public abstract class BaseObserver<T> {

    protected Context context;
    protected ProgressDialog progressDialog;
    protected boolean isShowDialog;

    public BaseObserver() {
    }

    public BaseObserver(Context context) {
        this.context = context;
    }

    public BaseObserver(Context context, boolean isShowDialog) {
        this.context = context;
        this.isShowDialog = isShowDialog;
    }

    protected void parserExceptionType(Throwable e) {
        if (e instanceof HttpException) {     //   HTTP错误
            onException(Const_http.BAD_NETWORK);
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {   //   连接错误
            onException(Const_http.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(Const_http.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {   //  解析错误
            onException(Const_http.PARSE_ERROR);
        } else {
            onException(Const_http.UNKNOWN_ERROR); // 未知错误
        }
    }

    /**
     * 请求异常
     *
     * @param reason
     */
    private void onException(int reason) {
        String message = "";
        switch (reason) {
            case Const_http.BAD_NETWORK:
                message = "网络错误";
//                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
                break;

            case Const_http.CONNECT_ERROR:
                message = "连接错误";
                break;

            case Const_http.CONNECT_TIMEOUT:
                message = "连接超时";
                break;

            case Const_http.PARSE_ERROR:
                message = "解析错误";
                break;

            case Const_http.UNKNOWN_ERROR:
                message = "未知错误";
                break;
            default:
                break;
        }
        ToastUtils.showShort(message);
        onFailed(String.valueOf(reason), message);
    }

    protected void onStart() {
    }

    protected abstract void onSuccess(T t);

    protected abstract void onFailed(String code, String message);

    protected void onFinish() {
    }

    protected void showDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.setCancelable(true);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                onCancleDialog();
            }
        });
        progressDialog.show();
    }

    protected void onCancleDialog() {
    }

    protected void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
