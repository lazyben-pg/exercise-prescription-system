package com.lazyben.exercise.entity;

public class ExercisePrescription {
    // 运动频率
    private ExerciseFrequency frequency;
    // 运动强度
    private ExerciserIntensity intensity;
    // 运动时间
    private ExerciseTime time;
    // 运动类型
    private ExerciseType type;

    public ExercisePrescription(ExerciseFrequency frequency, ExerciserIntensity intensity, ExerciseTime time, ExerciseType type) {
        this.frequency = frequency;
        this.intensity = intensity;
        this.time = time;
        this.type = type;
    }

    public ExerciseFrequency getFrequency() {
        return frequency;
    }

    public void setFrequency(ExerciseFrequency frequency) {
        this.frequency = frequency;
    }

    public ExerciserIntensity getIntensity() {
        return intensity;
    }

    public void setIntensity(ExerciserIntensity intensity) {
        this.intensity = intensity;
    }

    public ExerciseTime getTime() {
        return time;
    }

    public void setTime(ExerciseTime time) {
        this.time = time;
    }

    public ExerciseType getType() {
        return type;
    }

    public void setType(ExerciseType type) {
        this.type = type;
    }
}
