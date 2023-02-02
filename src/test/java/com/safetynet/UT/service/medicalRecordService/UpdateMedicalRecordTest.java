package com.safetynet.UT.service.medicalRecordService;

import com.safetynet.dto.medicalRecord.UpdateMedicalRecordRequestDto;
import com.safetynet.exception.NoSuchMedicalRecordException;
import com.safetynet.mapper.MedicalRecordMapper;
import com.safetynet.model.MedicalRecord;
import com.safetynet.repository.MedicalRecordRepository;
import com.safetynet.service.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateMedicalRecordTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Mock
    private MedicalRecordMapper medicalRecordMapper;

    @InjectMocks
    private MedicalRecordService sut;

    @Test
    void testUpdateMedicalRecord() {
        // Given
        UpdateMedicalRecordRequestDto requestDto = new UpdateMedicalRecordRequestDto(
                "john",
                "boyd",
                "03/06/1984",
                List.of("aznol:350mg", "hydrapermazol:100mg"),
                List.of("nillacilan", "peanut")
        );
        MedicalRecord medicalRecord = MedicalRecord.builder()
                .firstName("john")
                .lastName("boyd")
                .birthdate("03/06/1984")
                .medications(List.of("aznol:400mg", "hydrapermazol:50mg"))
                .allergies(List.of("nillacilan"))
                .build();
        when(medicalRecordRepository.findByFirstNameAndLastName("john", "boyd")).thenReturn(Optional.of(medicalRecord));
        // When
        MedicalRecord result = sut.updateMedicalRecord(requestDto);
        // Then
        assertThat(result).usingRecursiveComparison().isEqualTo(medicalRecord);
    }

    @Test
    void testUpdateMedicalRecordWithNoMedicalRecord() {
        // Given
        UpdateMedicalRecordRequestDto requestDto = new UpdateMedicalRecordRequestDto(
                "john",
                "boyd",
                "03/06/1984",
                List.of("aznol:350mg", "hydrapermazol:100mg"),
                List.of("nillacilan")
        );
        when(medicalRecordRepository.findByFirstNameAndLastName("john", "boyd")).thenReturn(Optional.empty());
        // When
        ThrowingCallable throwingCallable = () -> sut.updateMedicalRecord(requestDto);
        // Then
        assertThatThrownBy(throwingCallable).isInstanceOf(NoSuchMedicalRecordException.class);
    }
}
