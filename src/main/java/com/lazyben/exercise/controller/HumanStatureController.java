package com.lazyben.exercise.controller;

import com.lazyben.exercise.entity.Constant;
import com.lazyben.exercise.entity.HumanStature;
import com.lazyben.exercise.service.HumanStatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HumanStatureController {
    private final HumanStatureService humanStatureService;

    @Autowired
    public HumanStatureController(HumanStatureService humanStatureService) {
        this.humanStatureService = humanStatureService;
    }

    /**
     * 从 request body 中获取人体参数信息，并调用分类模型获取人体体质分类结果
     *
     * @param statureData 人体参数
     * @return 人体体质分类结果
     */
    @PostMapping(path = "/humanstature")
    public String getHumanStature(@RequestBody HumanStature statureData) {
        double[] data = statureData.createData();
        return Constant.HUMANSTATURES[humanStatureService.getHumanStature(data)];
    }
}
