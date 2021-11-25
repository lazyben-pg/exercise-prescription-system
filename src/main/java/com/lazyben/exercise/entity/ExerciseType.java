package com.lazyben.exercise.entity;

public class ExerciseType {
    //有氧运动类型
    private String aerobicsType;
    //抗阻运动类型
    private String resistanceType;
    //柔韧性运动类型
    private String flexibilityType;

    public ExerciseType(String aerobicsType, String resistanceType, String flexibilityType) {
        this.aerobicsType = aerobicsType;
        this.resistanceType = resistanceType;
        this.flexibilityType = flexibilityType;
    }

    public String getAerobicsType() {
        return aerobicsType;
    }

    public void setAerobicsType(String aerobicsType) {
        this.aerobicsType = aerobicsType;
    }

    public String getResistanceType() {
        return resistanceType;
    }

    public void setResistanceType(String resistanceType) {
        this.resistanceType = resistanceType;
    }

    public String getFlexibilityType() {
        return flexibilityType;
    }

    public void setFlexibilityType(String flexibilityType) {
        this.flexibilityType = flexibilityType;
    }
}
