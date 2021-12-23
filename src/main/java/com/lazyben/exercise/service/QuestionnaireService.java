package com.lazyben.exercise.service;

import com.lazyben.exercise.entity.Questionnaire;
import com.lazyben.exercise.mapper.QuestionnaireMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionnaireService {
    private final QuestionnaireMapper questionnaireMapper;

    @Autowired
    public QuestionnaireService(QuestionnaireMapper questionnaireMapper) {
        this.questionnaireMapper = questionnaireMapper;
    }

    public void createQuestionnaire(int userid, String[] result) {
        questionnaireMapper.createQuestionnaire(userid, result[0], result[1], result[2], result[3], result[4], result[5],
                result[6], result[7], result[8], result[9], result[10], result[11], result[12], result[13], analyseSymptom(result));
    }

    public List<Questionnaire> getQuestionnaire(int userid) {
        return questionnaireMapper.getQuestionnaire(userid);
    }

    public List<Questionnaire> getQuestionnaireById(int id) {
        Questionnaire questionnaire = questionnaireMapper.getQuestionnaireById(id);
        if (questionnaire == null) return null;
        final List<Questionnaire> result = new ArrayList<>();
        result.add(questionnaire);
        return result;
    }

    public Integer analyseSymptom(String[] result) {
        List<Integer> Symptom = new ArrayList<>();
        if (result[0].equals("A")) Symptom.add(0);//"孕妇"
        if (result[5].equals("A")) Symptom.add(1);//"哮喘病人"
        if (result[10].equals("A")) Symptom.add(2);//"感染免疫缺陷病毒"
        if (result[7].equals("A")) Symptom.add(3);//"高血压患者"
        if (result[13].equals("A")) Symptom.add(4);//"帕金森氏病"
        if (result[6].equals("B")) Symptom.add(5);//"血脂异常患者"
        if (result[10].equals("B")) Symptom.add(6);//"多发性硬化病"
        if (result[5].equals("A")) Symptom.add(7);//"慢性阻塞性肺部疾病病人"
        if (result[8].equals("B")) Symptom.add(8);//"骨质疏松病"
        if (result[9].equals("A")) Symptom.add(9);//"关节炎"
        if (result[11].equals("A")) Symptom.add(10);//"伴有唐氏综合征的智力残疾患者"
        if (result[1].equals("B")) Symptom.add(11);//"心力衰竭病人"
        if (result[8].equals("C")) Symptom.add(12);//"纤维肌痛病人"
        if (result[3].equals("A")) Symptom.add(13);//"下肢有症状的外周动脉疾病病人"
        if (result[2].equals("A")) Symptom.add(14);//"心脏移植病人"
        if (result[2].equals("B")) Symptom.add(15);//"住院病人的心脏康复"
        if (result[4].equals("A")) Symptom.add(16);//"脑血管意外病人"
        if (result[9].equals("A")) Symptom.add(17);//"恶性肿瘤"
        if (result[6].equals("A")) Symptom.add(18);//"糖尿病患者"

        if (Symptom.size() == 0) return 19; //无病症
        if (Symptom.size() > 1) return 20; //多种疾病
        return Symptom.get(0);
    }
}
