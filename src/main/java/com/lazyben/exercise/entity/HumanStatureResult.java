package com.lazyben.exercise.entity;

import java.util.List;

public class HumanStatureResult {
    private final boolean isLogin;
    private final String msg;
    private final String status;
    private final String stature;
    private final List<HumanStature> data;

    public HumanStatureResult(boolean isLogin, String msg, String status, List<HumanStature> data, String stature) {
        this.isLogin = isLogin;
        this.msg = msg;
        this.status = status;
        this.stature = stature;
        this.data = data;
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

    public String getMsg() {
        return msg;
    }

    public String getStature() {
        return stature;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public List<HumanStature> getData() {
        return data;
    }
}
