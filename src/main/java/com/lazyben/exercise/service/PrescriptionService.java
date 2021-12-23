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
        final List<HumanStature> humanStatures = humanStatureService.getHumanStature(userid);
        humanStatures.sort(Comparator.comparing(HumanStature::getCreatedAt).reversed());
        HumanStature humanStature = humanStatures.isEmpty() ? null : humanStatures.get(0);
        final List<Questionnaire> questionnaires = questionnaireService.getQuestionnaire(userid);
        String symptom;
        if (!questionnaires.isEmpty()) {
            questionnaires.sort(Comparator.comparing(Questionnaire::getCreatedAt).reversed());
            symptom = Constant.SYMPTOM[questionnaires.get(0).getSymptom()];
        } else {
            // 未填写问卷，根据人体体质给运动处方
            return getPrescriptionHumanStature(humanStature);
        }

        if ("无病症".equals(symptom)) {
            // 填写了问卷，但无任何病症，根据人体体质给运动处方
            return getPrescriptionHumanStature(humanStature);
        } else {
            // 有相应的病症，根据问卷给运动处方
            return getPrescriptionFromQuestionnaireSymptom(symptom);
        }

    }

    private List<SearchResult> getPrescriptionHumanStature(HumanStature humanStature) {
        if (humanStature == null) return null;
        final String stature = Constant.HUMANSTATURES[humanStature.getStature()];
        if ("肥胖".equals(stature) || "超重".equals(stature)) {
            return nodeSearchService.getPrescription("超重或超胖", Constant.DEFAULT_NODE_TYPE);
        } else {
            return null;
        }
    }

    private List<SearchResult> getPrescriptionFromQuestionnaireSymptom(String symptom) {
        switch (symptom) {
            case "无病症":
            case "多种疾病":
                return null;
            case "孕妇":
                return nodeSearchService.getPrescription(symptom, "健康人群");
            default:
                return nodeSearchService.getPrescription(symptom, Constant.DEFAULT_NODE_TYPE);
        }
    }
}
