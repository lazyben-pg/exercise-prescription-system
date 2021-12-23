package com.lazyben.exercise.entity;

public class Constant {
    public static final String DEFAULT_NODE_TYPE = "症状";
    public static final String[] RELATIONSHIPS = new String[]{
            "有氧强度", "有氧时间", "有氧频率", "有氧方式",
            "抗阻强度", "抗阻时间", "抗阻频率", "抗阻方式",
            "柔韧性强度", "柔韧性时间", "柔韧性频率", "柔韧性方式"};
    public static final String[] HUMANSTATURES = new String[]{
            "低体重高脂肪", "低体重", "低脂肪", "脂肪过多", "标准",
            "低脂肪肌肉型", "肥胖", "肥胖临界", "超重", "肌肉型超重", "临界线"};
    public static final String[] SYMPTOM = new String[]{
            "孕妇", "哮喘病人", "感染免疫缺陷病毒", "高血压患者", "帕金森氏病", "血脂异常患者", "多发性硬化病", "慢性阻塞性肺部疾病病人",
            "骨质疏松病", "关节炎", "伴有唐氏综合征的智力残疾患者", "心力衰竭病人", "纤维肌痛病人", "下肢有症状的外周动脉疾病病人", "心脏移植病人",
            "住院病人的心脏康复", "脑血管意外病人", "恶性肿瘤", "糖尿病患者", "无病症", "多种病症"};
    public static int Questionnaire_Count = 14;
}
