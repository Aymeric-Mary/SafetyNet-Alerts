package com.safetynet.repository;

import com.safetynet.model.MedicalRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicalRecordRepository extends AbstractRepository {

    private List<MedicalRecord> medicalRecords;

    public MedicalRecordRepository() {
        this.medicalRecords = data.getMedicalrecords();
    }

    public List<MedicalRecord> findAll() {
        return medicalRecords;
    }

}