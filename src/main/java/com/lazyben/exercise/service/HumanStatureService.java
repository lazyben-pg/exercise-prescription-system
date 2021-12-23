package com.lazyben.exercise.service;

import com.lazyben.exercise.entity.HumanStature;
import com.lazyben.exercise.mapper.StatureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component
public class HumanStatureService {
    private final StatureMapper statureMapper;

    @Autowired
    public HumanStatureService(StatureMapper statureMapper) {
        this.statureMapper = statureMapper;
    }

    public List<HumanStature> createHumanStature(double[] data, HumanStature humanStature) {
        try {
            String[] arguments = new String[14];
            arguments[0] = "python3";
            arguments[1] = "src/main/resources/human_stature_predict.py";
            for (int i = 0; i < arguments.length - 2; i++) {
                arguments[i + 2] = String.valueOf(data[i]);
            }
            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            String line = in.readLine();
            process.waitFor();
            System.out.println(Arrays.toString(data));
            final int stature = Integer.parseInt(line);
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
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("人体体质模型调用失败");
        }
    }

    public List<HumanStature> getHumanStature(int userid) {
        return statureMapper.getStatureByUserId(userid);
    }
}
