package com.lazyben.exercise.service;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class HumanStatureService {
    public int getHumanStature(double[] data) {
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
            return Integer.parseInt(line);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("人体体质模型调用失败");
        }
    }
}