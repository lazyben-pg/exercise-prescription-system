package com.lazyben.exercise.entity;

import java.util.List;

public class QuestionnaireResult extends Result {
    private final List<Questionnaire> data;

    public QuestionnaireResult(String msg, String status) {
        this(msg, status, null);
    }

    public QuestionnaireResult(String msg, String status, List<Questionnaire> data) {
        super(msg, status);
        this.data = data;
    }

    public List<Questionnaire> getData() {
        return data;
    }
}
