package com.lazyben.exercise.controller;

import com.lazyben.exercise.entity.PrescriptionResult;
import com.lazyben.exercise.entity.SearchResult;
import com.lazyben.exercise.service.AuthService;
import com.lazyben.exercise.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
            if (prescriptions == null) return new PrescriptionResult("ok", "未查询到相应运动处方，请尝试录入问卷或体质数据");
            return new PrescriptionResult("查询成功", "ok", prescriptions);
        }).orElse(new PrescriptionResult("fail", "用户尚未登陆"));
    }
}
