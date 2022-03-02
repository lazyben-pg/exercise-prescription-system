package com.lazyben.exercise.entity;

import java.sql.Timestamp;
import java.util.Arrays;

public class PingPongPrescription {
    private int id;
    private int userid;
    private String forehandAttack;
    private String backhandScoopPass;
    private String backhandPush;
    private String fastLoopDrive;
    private String highSpinLoopDrive;
    private String race;
    private Timestamp createdAt;

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

    public String getForehandAttack() {
        return forehandAttack;
    }

    public void setForehandAttack(String forehandAttack) {
        this.forehandAttack = forehandAttack;
    }

    public String getBackhandScoopPass() {
        return backhandScoopPass;
    }

    public void setBackhandScoopPass(String backhandScoopPass) {
        this.backhandScoopPass = backhandScoopPass;
    }

    public String getBackhandPush() {
        return backhandPush;
    }

    public void setBackhandPush(String backhandPush) {
        this.backhandPush = backhandPush;
    }

    public String getFastLoopDrive() {
        return fastLoopDrive;
    }

    public void setFastLoopDrive(String fastLoopDrive) {
        this.fastLoopDrive = fastLoopDrive;
    }

    public String getHighSpinLoopDrive() {
        return highSpinLoopDrive;
    }

    public void setHighSpinLoopDrive(String highSpinLoopDrive) {
        this.highSpinLoopDrive = highSpinLoopDrive;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public static int[] parseResult(String s) {
        String[] split = s.split(",");
        int[] result = new int[2];
        result[0] = Integer.parseInt(split[0]);
        result[1] = Integer.parseInt(split[1]);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(parseResult("10,10")));
    }
}
