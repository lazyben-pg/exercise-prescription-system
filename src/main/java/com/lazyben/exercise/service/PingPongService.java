package com.lazyben.exercise.service;

import com.alibaba.fastjson.JSON;
import com.lazyben.exercise.entity.HumanStature;
import com.lazyben.exercise.entity.PingPongFreq;
import com.lazyben.exercise.entity.PingPongPrescription;
import com.lazyben.exercise.entity.SearchResult;
import com.lazyben.exercise.mapper.PingPongPrescriptionMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class PingPongService {
    private final HumanStatureService humanStatureService;
    private final PrescriptionService prescriptionService;
    private final PingPongPrescriptionMapper pingPongPrescriptionMapper;

    @Autowired
    public PingPongService(HumanStatureService humanStatureService, PrescriptionService prescriptionService, PingPongPrescriptionMapper pingPongPrescriptionMapper) {
        this.humanStatureService = humanStatureService;
        this.prescriptionService = prescriptionService;
        this.pingPongPrescriptionMapper = pingPongPrescriptionMapper;
    }

    private double hrr(int age, double HRRest, double intensity) {
        double HRMax = 207 - 0.7 * age;
        return (HRMax - HRRest) * intensity + HRRest;
    }

    private List<HeartData> getCalHeartRateData() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://192.168.1.135:8081/cal/heartrate");
        try {
            final CloseableHttpResponse response = client.execute(httpGet);
            return JSON.parseObject(EntityUtils.toString(response.getEntity()), ResultTmp.class).getData();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private PingPongFreq calFreqByHR(HeartData heartData, double minHR, double maxHR, int time) {
        if (heartData.getMean() < minHR) {
            if (heartData.getName().equals("对抗比赛"))
                return new PingPongFreq(heartData.getName(), -1, time + 10 + "min/d");
            return new PingPongFreq(heartData.getName(), 40, time + "min/d");
        } else if (heartData.getMean() > maxHR) {
            if (heartData.getName().equals("对抗比赛"))
                return new PingPongFreq(heartData.getName(), -1, time - 10 + "min/d");
            return new PingPongFreq(heartData.getName(), 20, time + "min/d");
        } else {
            if (heartData.getLine25() < minHR && heartData.getLine75() < maxHR) {
                if (heartData.getName().equals("对抗比赛"))
                    return new PingPongFreq(heartData.getName(), -1, time + 10 + "min/d");
                return new PingPongFreq(heartData.getName(), 40, time + "min/d");
            } else if (heartData.getLine25() > minHR && heartData.getLine75() > maxHR) {
                if (heartData.getName().equals("对抗比赛"))
                    return new PingPongFreq(heartData.getName(), -1, time - 10 + "min/d");
                return new PingPongFreq(heartData.getName(), 20, time + "min/d");
            } else {
                if (heartData.getName().equals("对抗比赛"))
                    return new PingPongFreq(heartData.getName(), -1, time + "min/d");
                return new PingPongFreq(heartData.getName(), 30, time + "min/d");
            }
        }
    }

    private int[] getMaxMinIntensityByRange(String range) {
        String rangeReg = "\\D*(\\d+)%-(\\d+)%HRR";
        Pattern rangePattern = Pattern.compile(rangeReg);
        Matcher matcher = rangePattern.matcher(range);
        if (matcher.find()) return new int[]{Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))};
        else {
            String singleReg = "\\D*(\\d+)%HRR";
            Pattern singlePattern = Pattern.compile(singleReg);
            matcher = singlePattern.matcher(range);
            if (matcher.find()) return new int[]{Integer.parseInt(matcher.group(1))};
        }
        return new int[]{};
    }

    private String getTimeUnit(String time) {
        String dayReg = "\\D*(\\d+)min/d\\D*";
        final Pattern dayPattern = Pattern.compile(dayReg);
        final Matcher matcher = dayPattern.matcher(time);
        if (matcher.find()) return matcher.group(1);
        return null;
    }

    private String[] splitResult(String s) {
        return s.split(",");
    }

    public int getFreq(String s) {
        return Integer.parseInt(splitResult(s)[0]);
    }

    public String getTime(String s) {
        String[] splitResult = splitResult(s);
        if (splitResult.length == 3 && splitResult[2].equals("*")) {
            return splitResult[1] + "，建议用户增加间歇休息时间";
        } else {
            return splitResult[1];
        }
    }

    public List<PingPongFreq> calFreq(int userid) {
        // 数据库存在数据，直接获取
        List<PingPongPrescription> pingPongPrescriptionByUserId = pingPongPrescriptionMapper.getPingPongPrescriptionByUserId(userid);
        if (!pingPongPrescriptionByUserId.isEmpty()) {
            PingPongPrescription pingPongPrescription = pingPongPrescriptionByUserId.get(pingPongPrescriptionByUserId.size() - 1);
            List<PingPongFreq> result = new ArrayList<>();
            result.add(new PingPongFreq("正手快攻", getFreq(pingPongPrescription.getForehandAttack()), getTime(pingPongPrescription.getForehandAttack())));
            result.add(new PingPongFreq("反手快拨", getFreq(pingPongPrescription.getBackhandScoopPass()), getTime(pingPongPrescription.getBackhandScoopPass())));
            result.add(new PingPongFreq("反手搓球", getFreq(pingPongPrescription.getBackhandPush()), getTime(pingPongPrescription.getBackhandPush())));
            result.add(new PingPongFreq("前冲弧圈", getFreq(pingPongPrescription.getFastLoopDrive()), getTime(pingPongPrescription.getFastLoopDrive())));
            result.add(new PingPongFreq("加转弧圈", getFreq(pingPongPrescription.getHighSpinLoopDrive()), getTime(pingPongPrescription.getHighSpinLoopDrive())));
            result.add(new PingPongFreq("对抗比赛", getFreq(pingPongPrescription.getRace()), getTime(pingPongPrescription.getRace())));
            return result;
        }


        // 获取用户年龄和静息心率
        final List<HumanStature> humanStatures = humanStatureService.getHumanStature(userid);
        humanStatures.sort(Comparator.comparing(HumanStature::getCreatedAt).reversed());
        HumanStature humanStature = humanStatures.isEmpty() ? null : humanStatures.get(0);
        if (humanStature == null) return null;
        int age = humanStature.getAge();
        double HRRest = humanStature.getHeartRateRest();

        // 获取用户处方中的有氧强度范围
        int maxIntensity = 0, minIntensity = 0;
        final AtomicInteger time = new AtomicInteger(0);
        final List<SearchResult> prescriptions = prescriptionService.getPrescription(userid);
        if (prescriptions == null) return null;
        for (SearchResult oneRelationshipPrescription : prescriptions) {
            if ("有氧强度".equals(oneRelationshipPrescription.getRelationship())) {
                String range = oneRelationshipPrescription.getResult().get(0);
                final int[] maxMinIntensity = getMaxMinIntensityByRange(range);
                if (maxMinIntensity.length == 0) return null;
                if (maxMinIntensity.length == 1) return null;
                if (maxMinIntensity.length == 2) {
                    minIntensity = maxMinIntensity[0];
                    maxIntensity = maxMinIntensity[1];
                }
            }
            if ("有氧时间".equals(oneRelationshipPrescription.getRelationship())) {
                final String timeStr = oneRelationshipPrescription.getResult().get(0);
                final String timeUnit = getTimeUnit(timeStr);
                if (timeUnit == null) time.set(-1);
                else time.set(Integer.parseInt(timeUnit));
            }
        }

        double minHR = hrr(age, HRRest, minIntensity * 1.0 / 100);
        double maxHR = hrr(age, HRRest, maxIntensity * 1.0 / 100);
        try {
            final List<HeartData> calHeartRateData = getCalHeartRateData();
            List<PingPongFreq> collect = calHeartRateData.stream()
                    .map(heartData -> calFreqByHR(heartData, minHR, maxHR, time.get()))
                    .collect(Collectors.toList());
            insertIntoDataBase(userid, collect);
            return collect;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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


class ResultTmp {
    private List<HeartData> data;

    public List<HeartData> getData() {
        return data;
    }

    public void setData(List<HeartData> data) {
        this.data = data;
    }
}

class HeartData {
    private String name;
    private double line25;
    private double line50;
    private double line75;
    private double mean;
    private double std;
    private double max;
    private double min;
    private double count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLine25() {
        return line25;
    }

    public void setLine25(double line25) {
        this.line25 = line25;
    }

    public double getLine50() {
        return line50;
    }

    public void setLine50(double line50) {
        this.line50 = line50;
    }

    public double getLine75() {
        return line75;
    }

    public void setLine75(double line75) {
        this.line75 = line75;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getStd() {
        return std;
    }

    public void setStd(double std) {
        this.std = std;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}