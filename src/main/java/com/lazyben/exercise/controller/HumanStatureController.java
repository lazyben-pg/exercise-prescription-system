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
//        System.out.println(111);
//        System.out.println(statureData);
        double[] data = statureData.createData();
        System.out.println(statureData);
//        double[] data = new double[]{0., 18., 175., 53.9, 46.1, 49.3, 4.6, 8.5, 17.6, 11.2, 65.1, 1733.3};
        return Constant.HUMANSTATURES[humanStatureService.getHumanStature(data)];
    }
}
