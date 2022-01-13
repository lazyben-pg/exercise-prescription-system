package com.lazyben.exercise.service;

import com.alibaba.fastjson.JSON;
import com.lazyben.exercise.entity.HumanStature;
import com.lazyben.exercise.entity.PingPongFreq;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PingPongService {
    private final HumanStatureService humanStatureService;

    @Autowired
    public PingPongService(HumanStatureService humanStatureService) {
        this.humanStatureService = humanStatureService;
    }

    private double hrr(int age, double HRRest, double intensity) {
        double HRMax = 207 - 0.7 * age;
        return (HRMax - HRRest) * intensity + HRRest;
    }

    private List<HeartData> getCalHeartRateData() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://192.168.1.134:8081/cal/heartrate");
        try {
            final CloseableHttpResponse response = client.execute(httpGet);
            return JSON.parseObject(EntityUtils.toString(response.getEntity()), ResultTmp.class).getData();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private PingPongFreq calFreqByHR(HeartData heartData, double minHR, double maxHR) {
        if (heartData.getName().equals("对抗比赛")) return new PingPongFreq(heartData.getName(), -1);
        if (heartData.getMean() < minHR) {
            return new PingPongFreq(heartData.getName(), 40);
        } else if (heartData.getMean() > maxHR) {
            return new PingPongFreq(heartData.getName(), 20);
        } else {
            if (heartData.getLine25() < minHR && heartData.getLine75() < maxHR) {
                return new PingPongFreq(heartData.getName(), 40);
            } else if (heartData.getLine25() > minHR && heartData.getLine75() > maxHR) {
                return new PingPongFreq(heartData.getName(), 20);
            } else {
                return new PingPongFreq(heartData.getName(), 30);
            }
        }
    }

    public List<PingPongFreq> calFreq(int userid) {
        final List<HumanStature> humanStatures = humanStatureService.getHumanStature(userid);
        humanStatures.sort(Comparator.comparing(HumanStature::getCreatedAt).reversed());
        HumanStature humanStature = humanStatures.isEmpty() ? null : humanStatures.get(0);
        if (humanStature == null) return null;
        int age = humanStature.getAge();
        double HRRest = humanStature.getHeartRateRest();
        double minHR = hrr(age, HRRest, 0.4);
        double maxHR = hrr(age, HRRest, 0.59);
        try {
            final List<HeartData> calHeartRateData = getCalHeartRateData();
            return calHeartRateData.stream().map(heartData -> calFreqByHR(heartData, minHR, maxHR)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
