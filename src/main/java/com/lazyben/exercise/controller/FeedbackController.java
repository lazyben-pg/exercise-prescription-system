package com.lazyben.exercise.controller;

import com.lazyben.exercise.entity.FeedbackResult;
import com.lazyben.exercise.service.AuthService;
import com.lazyben.exercise.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackController {
    private final AuthService authService;
    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(AuthService authService, FeedbackService feedbackService) {
        this.authService = authService;
        this.feedbackService = feedbackService;
    }

    @GetMapping(path = "/feedback")
    public FeedbackResult getFeedback(@RequestParam("level") int level) {
        return authService.getCurrentUser()
                .map((loggedInUser) -> FeedbackResult.success(feedbackService.update(loggedInUser.getId(), level)))
                .orElse(FeedbackResult.failure("用户尚未登陆"));
    }
}
