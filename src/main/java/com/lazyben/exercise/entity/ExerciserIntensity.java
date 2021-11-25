package com.lazyben.exercise.entity;

public class ExerciserIntensity {
    //有氧运动强度
    private String aerobicsIntensity;
    //抗阻运动强度
    private String resistanceIntensity;
    //柔韧性运动强度
    private String flexibilityIntensity;

    public ExerciserIntensity(String aerobicsIntensity, String resistanceIntensity, String flexibilityIntensity) {
        this.aerobicsIntensity = aerobicsIntensity;
        this.resistanceIntensity = resistanceIntensity;
        this.flexibilityIntensity = flexibilityIntensity;
    }

    public String getAerobicsIntensity() {
        return aerobicsIntensity;
    }

    public void setAerobicsIntensity(String aerobicsIntensity) {
        this.aerobicsIntensity = aerobicsIntensity;
    }

    public String getResistanceIntensity() {
        return resistanceIntensity;
    }

    public void setResistanceIntensity(String resistanceIntensity) {
        this.resistanceIntensity = resistanceIntensity;
    }

    public String getFlexibilityIntensity() {
        return flexibilityIntensity;
    }

    public void setFlexibilityIntensity(String flexibilityIntensity) {
        this.flexibilityIntensity = flexibilityIntensity;
    }
}
