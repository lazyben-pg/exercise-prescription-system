package com.lazyben.exercise.entity;

public class PingPongFreq {
    private String actionName;
    private int freq;

    public PingPongFreq(String actionName, int freq) {
        this.actionName = actionName;
        this.freq = freq;
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
