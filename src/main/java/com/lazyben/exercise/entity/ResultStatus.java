package com.lazyben.exercise.entity;

public enum ResultStatus {
    OK("ok"),
    FAIL("fail");

    final String status;

    ResultStatus(String status) {
        this.status = status;
    }
}
