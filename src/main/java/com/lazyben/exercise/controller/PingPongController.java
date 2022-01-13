package com.lazyben.exercise.controller;

import com.lazyben.exercise.entity.PingPongFreq;
import com.lazyben.exercise.entity.PingPongResult;
import com.lazyben.exercise.service.AuthService;
import com.lazyben.exercise.service.PingPongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PingPongController {
    private final AuthService authService;
    private final PingPongService pingPongService;

    @Autowired
    public PingPongController(AuthService authService, PingPongService pingPongService) {
        this.authService = authService;
        this.pingPongService = pingPongService;
    }

    @GetMapping("/pingpong")
    public PingPongResult getPingPongPrescription() {
        return authService.getCurrentUser().map((loggedInUser) -> {
            final List<PingPongFreq> pingPongFreqs = pingPongService.calFreq(loggedInUser.getId());
            if (pingPongFreqs == null) return PingPongResult.success("请先填写体质参数问卷");
            return PingPongResult.success("查询成功", pingPongFreqs);
        }).orElse(PingPongResult.failure("用户尚未登陆"));
    }
}
