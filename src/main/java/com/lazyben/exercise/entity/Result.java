package com.lazyben.exercise.entity;

public abstract class Result<T> {
    private final String msg;
    private final ResultStatus status;
    private final T data;

    public Result(String msg, ResultStatus status, T data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
