package com.safetynet.UT.repository;

import com.safetynet.model.FireStation;
import com.safetynet.model.MedicalRecord;
import com.safetynet.repository.MedicalRecordRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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

    @Test
    void findByFirstNameAndLastNameTest() {
        // Given
        String firstName = "John";
        String lastName = "Boyd";
        // When
        MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName).get();
        // Then
        MedicalRecord expectedMedicalRecord = MedicalRecord.builder()
                .firstName("John")
                .lastName("Boyd")
                .birthdate("03/06/1984")
                .medications(List.of("aznol:350mg", "hydrapermazol:100mg"))
                .allergies(List.of("nillacilan"))
                .build();
        assertThat(medicalRecord)
                .usingRecursiveComparison()
                .ignoringFields("person")
                .isEqualTo(expectedMedicalRecord);
    }

    @Test
    void saveTest_withNewMedicalRecord(){
        // Given
        MedicalRecord medicalRecord = MedicalRecord.builder()
                .firstName("John")
                .lastName("Doe")
                .birthdate("03/06/1984")
                .medications(List.of("aznol:350mg", "hydrapermazol:100mg"))
                .allergies(List.of("nillacilan"))
                .build();
        // When
        MedicalRecord result = medicalRecordRepository.save(medicalRecord);
        // Then
        Optional<MedicalRecord> actualMedicalRecord = medicalRecordRepository.findByFirstNameAndLastName("John", "Doe");
        assertThat(actualMedicalRecord).isPresent();
        assertThat(actualMedicalRecord.get())
                .usingRecursiveComparison()
                .isEqualTo(medicalRecord);
        assertThat(result).isEqualTo(actualMedicalRecord.get());

    }

    @Test
    void saveTest_withExistingMedicalRecord(){
        // Given
        MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName("John", "Boyd").get();
        medicalRecord.setBirthdate("01/01/2000");
        // When
        MedicalRecord result = medicalRecordRepository.save(medicalRecord);
        // Then
        MedicalRecord actualMedicalRecord = medicalRecordRepository.findByFirstNameAndLastName("John", "Boyd").get();
        assertThat(actualMedicalRecord.getBirthdate()).isEqualTo("01/01/2000");
        assertThat(result).isEqualTo(medicalRecord);
    }

    @Test
    void deleteTest(){
        // Given
        MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName("John", "Boyd").get();
        // When
        medicalRecordRepository.delete(medicalRecord);
        // Then
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        assertThat(medicalRecords).doesNotContain(medicalRecord);
    }

}