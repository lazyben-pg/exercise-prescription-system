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

    @PostMapping("/humanstature")
    public String getHumanStature(@RequestBody HumanStature statureData) {
        double[] data = statureData.createData();
        return Constant.HUMANSTATURES[humanStatureService.getHumanStature(data)];
    }
}
