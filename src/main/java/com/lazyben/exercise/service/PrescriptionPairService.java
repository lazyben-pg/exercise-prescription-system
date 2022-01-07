package com.lazyben.exercise.service;

import com.lazyben.exercise.entity.PrescriptionPair;
import com.lazyben.exercise.mapper.PrescriptionPairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrescriptionPairService {
    private final PrescriptionPairMapper prescriptionPairMapper;

    @Autowired
    public PrescriptionPairService(PrescriptionPairMapper prescriptionPairMapper) {
        this.prescriptionPairMapper = prescriptionPairMapper;
    }

    public List<PrescriptionPair> getPrescriptionPairs(int id) {
        return prescriptionPairMapper.getPrescriptionPairByUserId(id);
    }

    public void createPrescriptionPair(int userId,int questionnaireId, int humanStatureId) {
        prescriptionPairMapper.createPrescriptionPair(userId, questionnaireId, humanStatureId);
    }
}
