package com.lazyben.exercise.entity;

public class ExerciseFrequency {
    //有氧频率
    private String aerobicsFrequency;
    //抗阻频率
    private String resistanceFrequency;
    //柔韧性频率
    private String flexibilityFrequency;

    public ExerciseFrequency(String aerobicsFrequency, String resistanceFrequency, String flexibilityFrequency) {
        this.aerobicsFrequency = aerobicsFrequency;
        this.resistanceFrequency = resistanceFrequency;
        this.flexibilityFrequency = flexibilityFrequency;
    }

    public String getAerobicsFrequency() {
        return aerobicsFrequency;
    }

    public void setAerobicsFrequency(String aerobicsFrequency) {
        this.aerobicsFrequency = aerobicsFrequency;
    }

    public String getResistanceFrequency() {
        return resistanceFrequency;
    }

    public void setResistanceFrequency(String resistanceFrequency) {
        this.resistanceFrequency = resistanceFrequency;
    }

    public String getFlexibilityFrequency() {
        return flexibilityFrequency;
    }

    public void setFlexibilityFrequency(String flexibilityFrequency) {
        this.flexibilityFrequency = flexibilityFrequency;
    }
}
