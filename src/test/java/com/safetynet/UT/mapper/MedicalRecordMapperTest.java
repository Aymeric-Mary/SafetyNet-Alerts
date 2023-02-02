package com.safetynet.UT.mapper;

import com.safetynet.dto.medicalRecord.CreateMedicalRecordRequestDto;
import com.safetynet.dto.medicalRecord.UpdateMedicalRecordRequestDto;
import com.safetynet.mapper.MedicalRecordMapper;
import com.safetynet.mapper.MedicalRecordMapperImpl;
import com.safetynet.model.MedicalRecord;
import com.safetynet.model.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MedicalRecordMapperTest {

    private final MedicalRecordMapper medicalRecordMapper = new MedicalRecordMapperImpl();

    @Test
    public void toMedicalRecordTest() {
        // Given
        CreateMedicalRecordRequestDto requestDto = new CreateMedicalRecordRequestDto(
                "John",
                "Boyd",
                "03/06/1984",
                List.of("aznol:350mg", "hydrapermazol:100mg"),
                List.of("nillacilan")
        );
        Person person = Person.builder()
                .firstName("John")
                .lastName("Boyd")
                .address("1509 Culver St")
                .city("Culver")
                .zip("97451")
                .phone("841-874-6512")
                .email("jaboyd@email.com")
                .build();
        // When
        MedicalRecord medicalRecord = medicalRecordMapper.toMedicalRecord(requestDto, person);
        // Then
        MedicalRecord expectedMedicalRecord = MedicalRecord.builder()
                .firstName("John")
                .lastName("Boyd")
                .birthdate("03/06/1984")
                .medications(List.of("aznol:350mg", "hydrapermazol:100mg"))
                .allergies(List.of("nillacilan"))
                .person(person)
                .build();
        assertThat(medicalRecord).usingRecursiveComparison().isEqualTo(expectedMedicalRecord);
    }

    @Test
    public void mapUpdateRequestDtoTest() {
        // Given
        MedicalRecord medicalRecord = MedicalRecord.builder()
                .firstName("John")
                .lastName("Boyd")
                .birthdate("03/06/1984")
                .medications(new ArrayList<>(List.of("aznol:350mg", "hydrapermazol:100mg")))
                .allergies(new ArrayList<>(List.of("nillacilan")))
                .build();
        UpdateMedicalRecordRequestDto requestDto = new UpdateMedicalRecordRequestDto(
                "John",
                "Boyd",
                "08/30/1979",
                List.of("thradox:700mg"),
                List.of("illisoxian")
        );
        // When
        medicalRecordMapper.mapUpdateRequestDto(medicalRecord, requestDto);
        // Then
        MedicalRecord expectedMedicalRecord = MedicalRecord.builder()
                .firstName("John")
                .lastName("Boyd")
                .birthdate("08/30/1979")
                .medications(List.of("thradox:700mg"))
                .allergies(List.of("illisoxian"))
                .build();
        assertThat(medicalRecord).usingRecursiveComparison().isEqualTo(expectedMedicalRecord);
    }
}
