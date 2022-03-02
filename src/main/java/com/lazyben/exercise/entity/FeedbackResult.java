package com.lazyben.exercise.entity;

import java.util.List;

public class FeedbackResult extends Result<String> {


    private FeedbackResult(String msg, ResultStatus status, String data) {
        super(msg, status, data);
    }

    public static FeedbackResult failure(String msg) {
        return new FeedbackResult(msg, ResultStatus.FAIL, null);
    }

    public static FeedbackResult success(String msg) {
        return new FeedbackResult(msg, ResultStatus.OK, null);
    }

    public static FeedbackResult success(String msg, String data) {
        return new FeedbackResult(msg, ResultStatus.OK, data);
    }
}
