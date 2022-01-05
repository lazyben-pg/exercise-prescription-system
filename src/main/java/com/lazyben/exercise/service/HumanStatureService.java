package com.lazyben.exercise.service;

import com.alibaba.fastjson.JSON;
import com.lazyben.exercise.entity.HumanStature;
import com.lazyben.exercise.mapper.StatureMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
public class HumanStatureService {
    private final StatureMapper statureMapper;

    @Autowired
    public HumanStatureService(StatureMapper statureMapper) {
        this.statureMapper = statureMapper;
    }

    public int getHumanStatureByPost(double[] data) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://192.168.1.134:5000/cal/stature");
        final HashMap<String, double[]> map = new HashMap<>();
        map.put("data", data);
        try {
            StringEntity entity = new StringEntity(JSON.toJSONString(map));
            httpPost.setEntity(entity);
            CloseableHttpResponse response = client.execute(httpPost);
            final HttpEntity entity1 = response.getEntity();
            return  Integer.parseInt(EntityUtils.toString(entity1));
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public List<HumanStature> createHumanStature(double[] data, HumanStature humanStature) {
        try {
//            String[] arguments = new String[14];
//            arguments[0] = "python3";
//            arguments[1] = "src/main/resources/human_stature_predict.py";
//            for (int i = 0; i < arguments.length - 2; i++) {
//                arguments[i + 2] = String.valueOf(data[i]);
//            }
//            getHumanStatureByPost(data);
//            Process process = Runtime.getRuntime().exec(arguments);
//            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
//            String line = in.readLine();
//            process.waitFor();
//            final int stature = Integer.parseInt(line);
            final  int stature = getHumanStatureByPost(data);
            humanStature.setStature(stature);
            statureMapper.createHumanStature(humanStature.getUserid(),
                    humanStature.getSexual(),
                    humanStature.getAge(),
                    humanStature.getHeight(),
                    humanStature.getWeight(),
                    humanStature.getMuscleMass(),
                    humanStature.getLeanBodyMass(),
                    humanStature.getFatWeight(),
                    humanStature.getFatPercentage(),
                    humanStature.getBodyMassIndex(),
                    humanStature.getWeightControl(),
                    humanStature.getStandardWeight(),
                    humanStature.getBasalMetabolicRate(),
                    humanStature.getStature());
            return statureMapper.getStatureByUserId(humanStature.getUserid());
        } catch (IOException e) {
            throw new RuntimeException("人体体质模型调用失败");
        }
    }

    public List<HumanStature> getHumanStature(int userid) {
        return statureMapper.getStatureByUserId(userid);
    }

    public List<HumanStature> getHumanStatureById(int id) {
        final HumanStature stature = statureMapper.getStatureById(id);
        if(stature == null) return null;
        List<HumanStature> result = new ArrayList<>();
        result.add(stature);
        return result;
    }
}
