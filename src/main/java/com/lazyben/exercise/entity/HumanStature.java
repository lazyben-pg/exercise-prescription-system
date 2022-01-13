package com.lazyben.exercise.entity;

import java.sql.Timestamp;

public class HumanStature {
    private int id;
    private int userid;
    private double heartRateRest;
    private int sexual; // give
    private int age; // give
    private double height; //give
    private double weight; // give
    private double muscleMass; // give
    private double leanBodyMass; // weight - fatWeight
    private double fatWeight; // give
    private double fatPercentage; // give
    private double bodyMassIndex; // give & cal
    private double weightControl; // cal
    private double standardWeight; // cal
    private double basalMetabolicRate; //give
    private int stature;
    private Timestamp createdAt;

    public double getHeartRateRest() {
        return heartRateRest;
    }

    public void setHeartRateRest(double heartRateRest) {
        this.heartRateRest = heartRateRest;
    }

    public int getSexual() {
        return sexual;
    }

    public void setSexual(int sexual) {
        this.sexual = sexual;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getMuscleMass() {
        return muscleMass;
    }

    public void setMuscleMass(double muscleMass) {
        this.muscleMass = muscleMass;
    }

    public double getFatWeight() {
        return fatWeight;
    }

    public void setFatWeight(double fatWeight) {
        this.fatWeight = fatWeight;
    }

    public double getFatPercentage() {
        return fatPercentage;
    }

    public void setFatPercentage(double fatPercentage) {
        this.fatPercentage = fatPercentage;
    }

    public double getBodyMassIndex() {
        return bodyMassIndex;
    }

    public double getLeanBodyMass() {
        return leanBodyMass;
    }

    public void setLeanBodyMass(double leanBodyMass) {
        this.leanBodyMass = leanBodyMass;
    }

    public double getWeightControl() {
        return weightControl;
    }

    public void setWeightControl(double weightControl) {
        this.weightControl = weightControl;
    }

    public double getStandardWeight() {
        return standardWeight;
    }

    public void setStandardWeight(double standardWeight) {
        this.standardWeight = standardWeight;
    }

    public void setBodyMassIndex(double bodyMassIndex) {
        this.bodyMassIndex = bodyMassIndex;
    }

    public double getBasalMetabolicRate() {
        return basalMetabolicRate;
    }

    public void setBasalMetabolicRate(double basalMetabolicRate) {
        this.basalMetabolicRate = basalMetabolicRate;
    }

    public double[] createData() {
        this.leanBodyMass = weight - fatWeight;
        this.standardWeight = (height - (1 - sexual) * 80 - sexual * 70) * ((1 - sexual) * 0.7 + sexual * 0.6);
        this.weightControl = this.standardWeight - weight;
        return new double[]{sexual, age, height, weight, muscleMass, leanBodyMass, fatWeight, fatPercentage, bodyMassIndex, weightControl, standardWeight, basalMetabolicRate};
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "HumanStature{" +
                "sexual=" + sexual +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", muscleMass=" + muscleMass +
                ", leanBodyMass=" + leanBodyMass +
                ", fatWeight=" + fatWeight +
                ", fatPercentage=" + fatPercentage +
                ", BMI=" + bodyMassIndex +
                ", weightControl=" + weightControl +
                ", standardWeight=" + standardWeight +
                ", basalMetabolicRate=" + basalMetabolicRate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getStature() {
        return stature;
    }

    public void setStature(int stature) {
        this.stature = stature;
    }
}
