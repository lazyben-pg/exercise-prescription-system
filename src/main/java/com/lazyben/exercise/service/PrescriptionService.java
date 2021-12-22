package com.lazyben.exercise.service;

import com.lazyben.exercise.entity.Constant;
import com.lazyben.exercise.entity.HumanStature;
import com.lazyben.exercise.entity.Questionnaire;
import com.lazyben.exercise.entity.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class PrescriptionService {
    private final HumanStatureService humanStatureService;
    private final QuestionnaireService questionnaireService;
    private final NodeSearchService nodeSearchService;

    public PrescriptionService(HumanStatureService humanStatureService,
                               QuestionnaireService questionnaireService,
                               NodeSearchService nodeSearchService) {
        this.humanStatureService = humanStatureService;
        this.questionnaireService = questionnaireService;
        this.nodeSearchService = nodeSearchService;
    }

    public List<SearchResult> getPrescription(int userid) {
        final HumanStature humanStature = humanStatureService.getHumanStature(userid);
        final List<Questionnaire> questionnaires = questionnaireService.getQuestionnaire(userid);

        if (humanStature == null && questionnaires.isEmpty()) return null;
        else if (!questionnaires.isEmpty()) {
            questionnaires.sort(Comparator.comparing(Questionnaire::getCreatedAt).reversed());
            final String symptom = Constant.SYMPTOM[questionnaires.get(0).getSymptom()];
            return nodeSearchService.getPrescription(symptom, Constant.DEFAULT_NODE_TYPE);
        } else {
            final String stature = Constant.HUMANSTATURES[humanStature.getStature()];
            if ("肥胖".equals(stature) || "超重".equals(stature)) {
                return nodeSearchService.getPrescription("超重或超胖", Constant.DEFAULT_NODE_TYPE);
            } else {
                return null;
            }
        }
    }
}
