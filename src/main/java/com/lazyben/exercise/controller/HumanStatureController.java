package com.lazyben.exercise.controller;

import com.lazyben.exercise.entity.Constant;
import com.lazyben.exercise.entity.HumanStature;
import com.lazyben.exercise.entity.HumanStatureResult;
import com.lazyben.exercise.service.AuthService;
import com.lazyben.exercise.service.HumanStatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
public class HumanStatureController {
    private final HumanStatureService humanStatureService;
    private final AuthService authService;

    @Autowired
    public HumanStatureController(HumanStatureService humanStatureService,
                                  AuthService authService) {
        this.humanStatureService = humanStatureService;
        this.authService = authService;
    }

    /**
     * 从 request body 中获取人体参数信息，并调用分类模型获取人体体质分类结果
     *
     * @param statureData 人体参数
     * @return 人体体质分类结果
     */
    @PostMapping(path = "/humanstature")
    public HumanStatureResult createHumanStature(@RequestBody HumanStature statureData) {
        return authService.getCurrentUser().map((loggedInUser) -> {
            double[] data = statureData.createData();
            statureData.setUserid(loggedInUser.getId());
            final List<HumanStature> humanStatures = humanStatureService.createHumanStature(data, statureData);
            humanStatures.sort(Comparator.comparing(HumanStature::getCreatedAt).reversed());
            return HumanStatureResult.success("模型调用成功", humanStatures, Constant.HUMANSTATURES[humanStatures.get(0).getStature()]);
        }).orElse(HumanStatureResult.failure("用户尚未登陆"));
    }

    @GetMapping(path = "/humanstature")
    public HumanStatureResult getHumanStature() {
        return authService.getCurrentUser().map((loggedInUser) -> {
            List<HumanStature> humanStatures = humanStatureService.getHumanStature(loggedInUser.getId());
            humanStatures.sort(Comparator.comparing(HumanStature::getCreatedAt).reversed());
            HumanStature humanStature = humanStatures.isEmpty() ? null : humanStatures.get(0);
            if (humanStature != null) {
                return HumanStatureResult.success("获取体质参数成功", humanStatures, Constant.HUMANSTATURES[humanStature.getStature()]);
            }
            return HumanStatureResult.success("用户尚未录入数据");
        }).orElse(HumanStatureResult.failure("用户尚未登陆"));
    }
}
