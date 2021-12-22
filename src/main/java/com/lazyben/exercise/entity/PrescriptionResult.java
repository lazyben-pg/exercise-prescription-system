package com.lazyben.exercise.entity;

import java.util.List;

public class PrescriptionResult {
    private final String msg;
    private final String status;
    private final List<SearchResult> data;

    public PrescriptionResult(String msg, String status, List<SearchResult> data) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public PrescriptionResult(String status, String msg) {
        this(msg, status, null);
    }


    public String getMsg() {
        return msg;
    }

    public String getStatus() {
        return status;
    }

    public List<SearchResult> getData() {
        return data;
    }
}
