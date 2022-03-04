package com.lazyben.exercise.entity;

import java.sql.Timestamp;

public class PingPongFreq {
    private String actionName;
    private int freq;
    private String time;
    private final Timestamp createdAt;

    public PingPongFreq(String actionName, int freq, String time) {
        this(actionName, freq, time, null);
    }

    public PingPongFreq(String actionName, int freq, String time, Timestamp timestamp) {
        this.actionName = actionName;
        this.freq = freq;
        this.time = time;
        this.createdAt = timestamp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

}
