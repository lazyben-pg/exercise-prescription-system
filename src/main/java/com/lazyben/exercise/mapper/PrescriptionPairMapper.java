package com.lazyben.exercise.mapper;

import com.lazyben.exercise.entity.PrescriptionPair;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PrescriptionPairMapper {
    @Insert("INSERT INTO prescription(userid, questionnaire_id, userinfo_id, created_at) VALUES(#{userId}, #{questionnaireId}, #{userinfoId}, now())")
    void createPrescriptionPair(@Param("userId") int userId, @Param("questionnaireId") int questionnaireId, @Param("userinfoId") int userInfoId);

    @Select("SELECT * from prescription where userid=#{userId}")
    List<PrescriptionPair> getPrescriptionPairByUserId(@Param("userId") int userId);
}
