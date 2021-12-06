package com.lazyben.exercise.entity;

public class UserResult {
    private final boolean isLogin;
    private final String msg;
    private final String status;
    private final User data;

    public UserResult(boolean isLogin, String msg, String status, User data) {
        this.isLogin = isLogin;
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public UserResult(String status, String msg) {
        this(false, msg, status, null);
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public String getStatus() {
        return status;
    }

    public User getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
