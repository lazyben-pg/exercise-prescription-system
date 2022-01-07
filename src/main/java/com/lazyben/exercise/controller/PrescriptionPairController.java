package com.lazyben.exercise.controller;

import com.lazyben.exercise.entity.PrescriptionPair;
import com.lazyben.exercise.entity.PrescriptionPairResult;
import com.lazyben.exercise.entity.PrescriptionResult;
import com.lazyben.exercise.entity.SearchResult;
import com.lazyben.exercise.service.AuthService;
import com.lazyben.exercise.service.PrescriptionPairService;
import com.lazyben.exercise.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrescriptionPairController {
    private final AuthService authService;
    private final PrescriptionPairService prescriptionPairService;

    @Autowired
    public PrescriptionPairController(AuthService authService, PrescriptionPairService prescriptionPairService) {
        this.authService = authService;
        this.prescriptionPairService = prescriptionPairService;
    }

    @GetMapping(path = "/pair")
    public PrescriptionPairResult getPrescription() {
        return authService.getCurrentUser().map((loggedInUser) -> {
            final List<PrescriptionPair> prescriptionPairs = prescriptionPairService.getPrescriptionPairs(loggedInUser.getId());
            if (prescriptionPairs == null) return PrescriptionPairResult.success("未查询到相应对");
            return PrescriptionPairResult.success("查询成功", prescriptionPairs);
        }).orElse(PrescriptionPairResult.failure("用户尚未登陆"));
    }
}
