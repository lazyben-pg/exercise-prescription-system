package com.lazyben.exercise.entity;

public class UserResult extends Result<User> {
    private final boolean isLogin;

    private UserResult(boolean isLogin, String msg, ResultStatus status, User data) {
        super(msg, status, data);
        this.isLogin = isLogin;
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public static UserResult failure(String msg) {
        return new UserResult(false, msg, ResultStatus.FAIL, null);
    }

    public static UserResult success(String msg, User data) {
        return new UserResult(true, msg, ResultStatus.OK, data);
    }

    public static UserResult success(boolean isLogin, String msg) {
        return new UserResult(isLogin, msg, ResultStatus.OK, null);
    }

    public static UserResult success(String msg) {
        return new UserResult(false, msg, ResultStatus.OK, null);
    }
}
