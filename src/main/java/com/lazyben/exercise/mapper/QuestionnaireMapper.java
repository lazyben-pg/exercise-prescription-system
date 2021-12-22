package com.lazyben.exercise.mapper;

import com.lazyben.exercise.entity.Questionnaire;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface QuestionnaireMapper {
    @Insert("insert into questionnaire(userid, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, symptom, created_at)" +
            "values(#{userid}, #{q1}, #{q2}, #{q3}, #{q4}, #{q5}, #{q6}, #{q7}, #{q8}, #{q9}, #{q10}, #{q11}, #{q12}, #{q13}, #{q14}, #{symptom},now())")
    void createQuestionnaire(@Param("userid") int userid,
                             @Param("q1") String q1,
                             @Param("q2") String q2,
                             @Param("q3") String q3,
                             @Param("q4") String q4,
                             @Param("q5") String q5,
                             @Param("q6") String q6,
                             @Param("q7") String q7,
                             @Param("q8") String q8,
                             @Param("q9") String q9,
                             @Param("q10") String q10,
                             @Param("q11") String q11,
                             @Param("q12") String q12,
                             @Param("q13") String q13,
                             @Param("q14") String q14,
                             @Param("symptom") int symptom);

    @Select("select * from questionnaire where userid=#{userid}")
    List<Questionnaire> getQuestionnaire(int userid);
}
