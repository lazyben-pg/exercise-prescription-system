package com.lazyben.exercise.entity;

import java.util.List;

public class PingPongsResult extends Result<List<List<PingPongFreq>>> {
    public PingPongsResult(String msg, ResultStatus status, List<List<PingPongFreq>> data) {
        super(msg, status, data);
    }

    public static PingPongsResult success(String msg, List<List<PingPongFreq>> data) {
        return new PingPongsResult(msg, ResultStatus.OK, data);
    }

    public static PingPongsResult failure(String msg) {
        return new PingPongsResult(msg, ResultStatus.FAIL, null);
    }

    public static PingPongsResult success(String msg) {
        return new PingPongsResult(msg, ResultStatus.OK, null);
    }
}
