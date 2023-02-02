package com.safetynet.repository;

import com.safetynet.exception.NoSuchMedicalRecordException;
import com.safetynet.model.MedicalRecord;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MedicalRecordRepository extends AbstractRepository {

    private List<MedicalRecord> medicalRecords;

    public MedicalRecordRepository() {
        this.medicalRecords = data.getMedicalrecords();
    }

    public List<MedicalRecord> findAll() {
        return medicalRecords;
    }

    public MedicalRecord save(MedicalRecord medicalRecord) {
        Optional<MedicalRecord> existingMedicalRecord = findByFirstNameAndLastName(
                medicalRecord.getFirstName(),
                medicalRecord.getLastName()
        );
        if (existingMedicalRecord.isPresent()) {
            existingMedicalRecord.get().setBirthdate(medicalRecord.getBirthdate());
            existingMedicalRecord.get().setMedications(medicalRecord.getMedications());
            existingMedicalRecord.get().setAllergies(medicalRecord.getAllergies());
            return existingMedicalRecord.get();
        }
        medicalRecords.add(medicalRecord);
        return medicalRecord;
    }

    public Optional<MedicalRecord> findByFirstNameAndLastName(String firstName, String lastName) {
        return medicalRecords.stream()
                .filter(medicalRecord -> medicalRecord.getFirstName().equals(firstName))
                .filter(medicalRecord -> medicalRecord.getLastName().equals(lastName))
                .findFirst();
    }

    public void delete(MedicalRecord medicalRecord) {
        Optional<MedicalRecord> existingMedicalRecord = findByFirstNameAndLastName(
                medicalRecord.getFirstName(),
                medicalRecord.getLastName()
        );
        if (existingMedicalRecord.isPresent()) {
            medicalRecords.remove(existingMedicalRecord.get());
        } else {
            throw new NoSuchMedicalRecordException(medicalRecord.getFirstName(), medicalRecord.getLastName());
        }
    }
}