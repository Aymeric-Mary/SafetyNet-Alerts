package com.safetynet.IT.repository;

import com.safetynet.model.MedicalRecord;
import com.safetynet.repository.MedicalRecordRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MedicalRecordRepositoryTest {

    private final MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();

    @Test
    void findAllTest() {
        // Given
        // When
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        // Then
        assertThat(medicalRecords.size()).isEqualTo(23);
        assertThat(medicalRecords)
                .flatExtracting("firstName", "lastName", "birthdate", "medications", "allergies","person")
                .doesNotContainNull();
    }

}