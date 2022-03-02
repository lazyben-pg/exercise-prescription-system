package com.lazyben.exercise.entity;

import java.util.List;

public class HumanStatureResult extends Result<List<HumanStature>> {
    private final String stature;

    private HumanStatureResult(String msg, ResultStatus status, List<HumanStature> data, String stature) {
        super(msg, status, data);
        this.stature = stature;
    }

    public String getStature() {
        return stature;
    }

    public static HumanStatureResult failure(String msg) {
        return new HumanStatureResult(msg, ResultStatus.FAIL, null, null);
    }

    public static HumanStatureResult success(String msg) {
        return new HumanStatureResult(msg, ResultStatus.OK, null, null);
    }

    public static HumanStatureResult success(String msg, List<HumanStature> data, String stature) {
        return new HumanStatureResult(msg, ResultStatus.OK, data, stature);
    }
}
