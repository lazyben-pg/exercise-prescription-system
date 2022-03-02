package com.lazyben.exercise.mapper;

import com.lazyben.exercise.entity.PingPongPrescription;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.parameters.P;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface PingPongPrescriptionMapper {
    @Select("SELECT * from pingpong_prescription where userid=#{userId}")
    List<PingPongPrescription> getPingPongPrescriptionByUserId(@Param("userId") int userId);

    @Insert("INSERT INTO pingpong_prescription(userid, forehand_attack, backhand_scoop_pass, backhand_push, fast_loop_drive, high_spin_loop_drive, race, created_at) VALUES(#{userId}, #{forehandAttack}, #{backhandScoopPass},#{backhandPush},#{fastLoopDrive},#{highSpinLoopDrive},#{race}, now())")
    void insertPingPongPrescription(@Param("userId") int userId,
                                    @Param("forehandAttack") String forehandAttack,
                                    @Param("backhandScoopPass") String backhandScoopPass,
                                    @Param("backhandPush") String backhandPush,
                                    @Param("fastLoopDrive") String fastLoopDrive,
                                    @Param("highSpinLoopDrive") String highSpinLoopDrive,
                                    @Param("race") String race);
}
