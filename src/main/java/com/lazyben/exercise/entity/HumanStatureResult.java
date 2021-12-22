package com.lazyben.exercise.entity;

public class HumanStatureResult {
    private final boolean isLogin;
    private final String msg;
    private final String status;
    private final String stature;
    private final HumanStature data;

    public HumanStatureResult(boolean isLogin, String msg, String status, HumanStature data, String stature) {
        this.isLogin = isLogin;
        this.msg = msg;
        this.status = status;
        this.data = data;
        this.stature = stature;
    }

    public HumanStatureResult(String status, String msg) {
        this(false, msg, status, null, null);
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public String getStatus() {
        return status;
    }

    public HumanStature getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public String getStature() {
        return stature;
    }
}
