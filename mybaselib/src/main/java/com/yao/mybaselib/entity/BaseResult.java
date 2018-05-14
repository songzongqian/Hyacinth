package com.yao.mybaselib.entity;

/**
 * Created by yao on 2017/12/28.
 */

public class BaseResult<T> {

    private static String SUCCESS_CODE = "000";

    private String code;
    private String message;
    private T data;

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(getCode());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
