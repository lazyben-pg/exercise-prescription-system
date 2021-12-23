package com.lazyben.exercise.entity;

import java.util.List;

public class QuestionnaireResult extends Result<List<Questionnaire>> {
    public QuestionnaireResult(String msg, ResultStatus status, List<Questionnaire> data) {
        super(msg, status, data);
    }

    public static QuestionnaireResult failure(String msg) {
        return new QuestionnaireResult(msg, ResultStatus.FAIL, null);
    }

    public static QuestionnaireResult success(String msg, List<Questionnaire> data) {
        return new QuestionnaireResult(msg, ResultStatus.OK, data);
    }

    public static QuestionnaireResult success(String msg) {
        return new QuestionnaireResult(msg, ResultStatus.OK, null);
    }
}
