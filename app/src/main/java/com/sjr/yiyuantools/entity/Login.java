package com.sjr.yiyuantools.entity;

public class Login {
    /**
     * err_code : 1
     * err_msg : 登录失败，用户名不存在或密码错误
     * uuid :
     * token :
     */

    private int err_code;
    private String err_msg;
    private String uuid;
    private String token;

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
