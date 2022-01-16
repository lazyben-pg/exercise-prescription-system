package com.lazyben.exercise.entity;

public class PingPongFreq {
    private String actionName;
    private int freq;
    private String time;

    public PingPongFreq(String actionName, int freq, String time) {
        this.actionName = actionName;
        this.freq = freq;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
