package com.bjpowernode.crm.commons.domain;

public class ReturnObject {
    private String code;//1表示成功 0失败
    private String message;//登录提示信息
    private Object retData;//返回其他数据

    public ReturnObject() {
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

    public Object getRetData() {
        return retData;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }
}
