package com.lan.common.vo;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;

public class BaseResultVo implements Serializable {

    private String result = "0000";//0000和9999
    private String msg = "success";//返回的信息，目前是在前端写
    private Object data;
    private PageInfo<?> pi;//pagehelper专用

    public BaseResultVo(){
    }



    public BaseResultVo(Object data) {
        this.data= data;
    }

    //常用set返回
    public void setResultMsg(String result, String msg) {
        this.result= result;
        this.msg = msg;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result= result;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg =msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data= data;
    }

    public PageInfo<?> getPi() {
        return pi;
    }

    public void setPi(PageInfo<?> pi) {
        this.pi = pi;
    }
}