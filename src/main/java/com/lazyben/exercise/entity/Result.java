package com.lazyben.exercise.entity;

public class Result {
    private final String msg;
    private final String status;

    public Result(String msg, String status) {
        this.msg = msg;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
