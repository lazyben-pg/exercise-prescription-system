package com.lazyben.exercise.entity;

import java.util.List;

public class PrescriptionResult extends Result<List<SearchResult>> {
    public PrescriptionResult(String msg, ResultStatus status, List<SearchResult> data) {
        super(msg, status, data);
    }

    public static PrescriptionResult failure(String msg) {
        return new PrescriptionResult(msg, ResultStatus.FAIL, null);
    }

    public static PrescriptionResult success(String msg, List<SearchResult> data) {
        return new PrescriptionResult(msg, ResultStatus.OK, data);
    }

    public static PrescriptionResult success(String msg) {
        return new PrescriptionResult(msg, ResultStatus.OK, null);
    }
}
