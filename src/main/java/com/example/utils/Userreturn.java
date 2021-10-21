package com.example.utils;

import java.util.List;
import java.util.Map;

public  class Userreturn<T> {
    private T data;

    private int code;
    private String message;
    private Boolean success;

    public Userreturn(Map map){
        this.success = true;
        this.code = 200;
        this.message = "操作成功";
        this.data = (T) map;
    }


    public Userreturn(){
        this.success = true;
        this.code = 200;
        this.message = "操作成功";
        this.data = (T) "";
    }

    public Userreturn(String message){
        this.success = false;
        this.code = 201;
        this.message = message;
        this.data = (T) "";
    }

    public Userreturn(int code, Map map){
        this.success = false;
        this.code = code;
        this.message = "操作成功";
        this.data = (T) map;
    }

    public Userreturn(int code, String msg,String data){
        this.success = false;
        this.code = code;
        this.message = "网页跳转";
        this.data = (T) data;
    }

    public Userreturn(int code, String data){
        this.success = false;
        this.code = code;
        this.message = "操作失败";
        this.data = (T) data;
    }

    public Userreturn(List data) {
        this.success = false;
        this.code = 200;
        this.message = "操作成功";
        this.data =(T) data;
    }

    public Userreturn(T data) {
        this.success = false;
        this.code = 200;
        this.message = "操作失败";
        this.data = data;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
