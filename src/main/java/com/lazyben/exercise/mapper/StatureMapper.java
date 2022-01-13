package com.lazyben.exercise.mapper;

import com.lazyben.exercise.entity.HumanStature;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StatureMapper {
    @Select("SELECT * FROM userinfo where userid=#{userid}")
    List<HumanStature> getStatureByUserId(@Param("userid") int userid);

    @Insert("INSERT INTO userinfo(userid, sexual, age, height, weight, muscle_mass, lean_body_mass, fat_weight, fat_percentage, body_mass_index, weight_control, standard_weight, basal_metabolic_rate, heart_rate_rest, stature, created_at) " +
            "VALUES(#{userid}, #{sexual}, #{age}, #{height}, #{weight}, #{muscleMass}, #{leanBodyMass}, #{fatWeight}, #{fatPercentage}, #{bodyMassIndex}, #{weightControl}, #{standardWeight}, #{basalMetabolicRate}, #{heartRateRest}, #{stature}, now())")
    void createHumanStature(@Param("userid") int userid,
                            @Param("sexual") int sexual,
                            @Param("age") int age,
                            @Param("height") double height,
                            @Param("weight") double weight,
                            @Param("muscleMass") double muscleMass,
                            @Param("leanBodyMass") double leanBodyMass,
                            @Param("fatWeight") double fatWeight,
                            @Param("fatPercentage") double fatPercentage,
                            @Param("bodyMassIndex") double bodyMassIndex,
                            @Param("weightControl") double weightControl,
                            @Param("standardWeight") double standardWeight,
                            @Param("basalMetabolicRate") double basalMetabolicRate,
                            @Param("heartRateRest") double heartRateRest,
                            @Param("stature") int stature);

    @Select("SELECT * FROM userinfo where id=#{id}")
    HumanStature getStatureById(int id);
}

