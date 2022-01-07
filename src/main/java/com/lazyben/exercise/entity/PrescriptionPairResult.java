package com.lazyben.exercise.entity;

import java.util.List;

public class PrescriptionPairResult extends Result<List<PrescriptionPair>> {
    public PrescriptionPairResult(String msg, ResultStatus status, List<PrescriptionPair> data) {
        super(msg, status, data);
    }

    public static PrescriptionPairResult success(String msg, List<PrescriptionPair> prescriptionPairs) {
        return new PrescriptionPairResult(msg, ResultStatus.OK, prescriptionPairs);
    }

    public static PrescriptionPairResult failure(String msg) {
        return new PrescriptionPairResult(msg, ResultStatus.FAIL, null);
    }

    public static PrescriptionPairResult success(String msg) {
        return new PrescriptionPairResult(msg, ResultStatus.OK, null);
    }
}
