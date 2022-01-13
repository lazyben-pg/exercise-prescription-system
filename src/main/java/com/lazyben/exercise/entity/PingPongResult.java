package com.lazyben.exercise.entity;

import java.util.List;

public class PingPongResult extends Result<List<PingPongFreq>> {
    public PingPongResult(String msg, ResultStatus status, List<PingPongFreq> data) {
        super(msg, status, data);
    }

    public static PingPongResult success(String msg, List<PingPongFreq> data) {
        return new PingPongResult(msg, ResultStatus.OK, data);
    }

    public static PingPongResult failure(String msg) {
        return new PingPongResult(msg, ResultStatus.FAIL, null);
    }

    public static PingPongResult success(String msg) {
        return new PingPongResult(msg, ResultStatus.OK, null);
    }
}
