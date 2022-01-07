package com.lazyben.exercise.controller;

import com.lazyben.exercise.entity.PrescriptionResult;
import com.lazyben.exercise.entity.SearchResult;
import com.lazyben.exercise.service.AuthService;
import com.lazyben.exercise.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final AuthService authService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService,
                                  AuthService authService) {
        this.prescriptionService = prescriptionService;
        this.authService = authService;
    }

    @GetMapping(path = "/prescription")
    public PrescriptionResult getPrescription() {
        return authService.getCurrentUser().map((loggedInUser) -> {
            final List<SearchResult> prescriptions = prescriptionService.getPrescription(loggedInUser.getId());
            if (prescriptions == null) return PrescriptionResult.success("未查询到相应运动处方，请尝试录入问卷或体质数据");
            return PrescriptionResult.success("查询成功", prescriptions);
        }).orElse(PrescriptionResult.failure("用户尚未登陆"));
    }

    @GetMapping(path = "/prescription/pair")
    public PrescriptionResult getPrescriptionByPair(@RequestParam("questionnaireId") int questionnaireId,
                                                    @RequestParam("userinfoId") int userInfoId){
        return authService.getCurrentUser().map((loggedInUser) -> {
            final List<SearchResult> prescriptions = prescriptionService.getPrescriptionByPair(loggedInUser.getId(), questionnaireId, userInfoId);
            if (prescriptions == null) return PrescriptionResult.success("当前运动处方不存在");
            return PrescriptionResult.success("查询成功", prescriptions);
        }).orElse(PrescriptionResult.failure("用户尚未登陆"));
    }
}
