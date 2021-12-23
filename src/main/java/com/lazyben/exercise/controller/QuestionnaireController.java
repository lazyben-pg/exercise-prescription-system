package com.lazyben.exercise.controller;

import com.lazyben.exercise.entity.Constant;
import com.lazyben.exercise.entity.Questionnaire;
import com.lazyben.exercise.entity.QuestionnaireResult;
import com.lazyben.exercise.service.AuthService;
import com.lazyben.exercise.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class QuestionnaireController {
    private final QuestionnaireService questionnaireService;
    private final AuthService authService;

    @Autowired
    public QuestionnaireController(QuestionnaireService questionnaireService, AuthService authService) {
        this.questionnaireService = questionnaireService;
        this.authService = authService;
    }

    @PostMapping("/questionnaire")
    public QuestionnaireResult createQuestionnaire(@RequestBody Map<String, String> questionnaire) {
        String[] result = new String[Constant.Questionnaire_Count];
        for (int i = 0; i < Constant.Questionnaire_Count; i++) {
            result[i] = questionnaire.get("q" + (i + 1));
        }

        return authService.getCurrentUser().map((loggedInUser) -> {
            questionnaireService.createQuestionnaire(loggedInUser.getId(), result);
            return QuestionnaireResult.success("问卷记录成功");
        }).orElse(QuestionnaireResult.failure("用户未登陆"));
    }

    @GetMapping("/questionnaires")
    public QuestionnaireResult getQuestionnaire() {
        return authService.getCurrentUser().map((loggedInUser) -> {
            final List<Questionnaire> data = questionnaireService.getQuestionnaire(loggedInUser.getId());
            if (data.isEmpty()) return QuestionnaireResult.success("当前用户尚未填写问卷");
            return QuestionnaireResult.success("查询成功", data);
        }).orElse(QuestionnaireResult.failure("用户未登陆"));
    }

    @RequestMapping("/questionnaire")
    public QuestionnaireResult getQuestionnaireById(@RequestParam("id") int id) {
        System.out.println(id);
        return authService.getCurrentUser().map((loggedInUser) -> {
            final List<Questionnaire> data = questionnaireService.getQuestionnaireById(id);
            if (data == null) return QuestionnaireResult.success("未查询到id为" + id + "的问卷信息");
            if (data.get(0).getUserid() != loggedInUser.getId())
                return QuestionnaireResult.success("id为" + id + "的问卷非当前用户的问卷");
            return QuestionnaireResult.success("查询成功", data);
        }).orElse(QuestionnaireResult.failure("用户未登陆"));
    }
}
