package com.example.asus.quartertou.bean;

import java.io.Serializable;

public class BaseBean implements Serializable {

    /**
     * msg : 无此用户
     * code : 1
     */

    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
