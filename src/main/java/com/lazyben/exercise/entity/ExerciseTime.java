package com.lazyben.exercise.entity;

public class ExerciseTime {
    //有氧时间
    private String aerobicsTime;
    //抗阻时间
    private String resistanceTime;
    //柔韧性时间
    private String flexibilityTime;

    public ExerciseTime(String aerobicsTime, String resistanceTime, String flexibilityTime) {
        this.aerobicsTime = aerobicsTime;
        this.resistanceTime = resistanceTime;
        this.flexibilityTime = flexibilityTime;
    }

    public String getAerobicsTime() {
        return aerobicsTime;
    }

    public void setAerobicsTime(String aerobicsTime) {
        this.aerobicsTime = aerobicsTime;
    }

    public String getResistanceTime() {
        return resistanceTime;
    }

    public void setResistanceTime(String resistanceTime) {
        this.resistanceTime = resistanceTime;
    }

    public String getFlexibilityTime() {
        return flexibilityTime;
    }

    public void setFlexibilityTime(String flexibilityTime) {
        this.flexibilityTime = flexibilityTime;
    }
}
