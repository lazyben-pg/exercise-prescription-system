package com.lazyben.exercise.service;

import com.lazyben.exercise.entity.PingPongFreq;
import com.lazyben.exercise.entity.PingPongPrescription;
import com.lazyben.exercise.mapper.PingPongPrescriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FeedbackService {
    private final PingPongPrescriptionMapper pingPongPrescriptionMapper;

    @Autowired
    public FeedbackService(PingPongPrescriptionMapper pingPongPrescriptionMapper) {
        this.pingPongPrescriptionMapper = pingPongPrescriptionMapper;
    }

    private String[] splitResult(String s) {
        return s.split(",");
    }

    public String update(int userid, int level) {
        List<PingPongPrescription> pingPongPrescriptions = pingPongPrescriptionMapper.getPingPongPrescriptionByUserId(userid);
        if (pingPongPrescriptions.isEmpty()) return "暂无乒乓处方结果，请先生成处方";


        PingPongPrescription pingPongPrescription = pingPongPrescriptions.get(pingPongPrescriptions.size() - 1);

        if(pingPongPrescription.getRace().split(",").length==3) return "该处方已经过评价，请上传新的心率数据";

        PingPongFreq forehandAttack = new PingPongFreq("正手快攻", Integer.parseInt(splitResult(pingPongPrescription.getForehandAttack())[0]), splitResult(pingPongPrescription.getForehandAttack())[1]);
        PingPongFreq backhandScoopPass = new PingPongFreq("反手快拨", Integer.parseInt(splitResult(pingPongPrescription.getBackhandScoopPass())[0]), splitResult(pingPongPrescription.getBackhandScoopPass())[1]);
        PingPongFreq backhandPush = new PingPongFreq("反手搓球", Integer.parseInt(splitResult(pingPongPrescription.getBackhandPush())[0]), splitResult(pingPongPrescription.getBackhandPush())[1]);
        PingPongFreq fastLoopDrive = new PingPongFreq("前冲弧圈", Integer.parseInt(splitResult(pingPongPrescription.getFastLoopDrive())[0]), splitResult(pingPongPrescription.getFastLoopDrive())[1]);
        PingPongFreq highSpinLoopDrive = new PingPongFreq("加转弧圈", Integer.parseInt(splitResult(pingPongPrescription.getHighSpinLoopDrive())[0]), splitResult(pingPongPrescription.getHighSpinLoopDrive())[1]);
        PingPongFreq race = new PingPongFreq("对抗比赛", Integer.parseInt(splitResult(pingPongPrescription.getRace())[0]), splitResult(pingPongPrescription.getRace())[1]);

        List<PingPongFreq> collect = new ArrayList<>();

        collect.add(forehandAttack);
        collect.add(backhandScoopPass);
        collect.add(backhandPush);
        collect.add(fastLoopDrive);
        collect.add(highSpinLoopDrive);
        collect.add(race);

        if (6 <= level && level < 16) {
            return "处方不修改";
        } else if (16 <= level && level <= 18) {
            collect.forEach(pingPongFreq -> {
                if (!Objects.equals(pingPongFreq.getActionName(), "对抗比赛")) {
                    pingPongFreq.setFreq(pingPongFreq.getFreq() - 5);
                    pingPongFreq.setTime(pingPongFreq.getTime() + ",*");
                } else
                    pingPongFreq.setTime(Integer.parseInt(getTimeUnit(pingPongFreq.getTime())) - 5 + "min/d,*");
            });
            insertIntoDataBase(userid, collect);
            return "处方修改";
        } else if (18 < level && level <= 20) {
            collect.forEach(pingPongFreq -> {
                if (!Objects.equals(pingPongFreq.getActionName(), "对抗比赛")) {
                    pingPongFreq.setFreq(pingPongFreq.getFreq() - 10);
                    pingPongFreq.setTime(pingPongFreq.getTime() + ",*");
                } else
                    pingPongFreq.setTime(Integer.parseInt(getTimeUnit(pingPongFreq.getTime())) - 10 + "min/d,*");
            });
            insertIntoDataBase(userid, collect);
            return "处方修改";
        } else {
            return "不合法";
        }
    }

    private String getTimeUnit(String time) {
        String dayReg = "\\D*(\\d+)min/d\\D*";
        final Pattern dayPattern = Pattern.compile(dayReg);
        final Matcher matcher = dayPattern.matcher(time);
        if (matcher.find()) return matcher.group(1);
        return null;
    }

    private void insertIntoDataBase(int userid, List<PingPongFreq> collect) {
        List<String> collect1 = collect.stream().map(r -> r.getFreq() + "," + r.getTime()).collect(Collectors.toList());
        pingPongPrescriptionMapper.insertPingPongPrescription(userid,
                collect1.get(0),
                collect1.get(1),
                collect1.get(2),
                collect1.get(3),
                collect1.get(4),
                collect1.get(5));
    }
}
