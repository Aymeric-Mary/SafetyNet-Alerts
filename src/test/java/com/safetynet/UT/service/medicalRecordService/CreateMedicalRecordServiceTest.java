package com.safetynet.UT.service.medicalRecordService;

import com.safetynet.dto.medicalRecord.CreateMedicalRecordRequestDto;
import com.safetynet.dto.medicalRecord.UpdateMedicalRecordRequestDto;
import com.safetynet.exception.MedicalRecordAlreadyExistException;
import com.safetynet.exception.NoSuchPersonException;
import com.safetynet.mapper.MedicalRecordMapper;
import com.safetynet.model.MedicalRecord;
import com.safetynet.model.Person;
import com.safetynet.repository.MedicalRecordRepository;
import com.safetynet.repository.PersonRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateMedicalRecordServiceTest {

    @Mock
    private MedicalRecordMapper medicalRecordMapper;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private MedicalRecordService sut;

    @Test
    void testCreateMedicalRecord() {
        // Given
        CreateMedicalRecordRequestDto requestDto = new CreateMedicalRecordRequestDto(
                "john",
                "boyd",
                "03/06/1984",
                List.of("aznol:350mg", "hydrapermazol:100mg"),
                List.of("nillacilan")
        );
        Person person = Person.builder()
                .firstName("john")
                .lastName("boyd")
                .address("1509 Culver St")
                .city("Culver")
                .zip("97451")
                .phone("841-874-6512")
                .email("jaboyd@email.com")
                .build();
        MedicalRecord expected = MedicalRecord.builder()
                .firstName("john")
                .lastName("boyd")
                .birthdate("03/06/1984")
                .medications(List.of("aznol:350mg", "hydrapermazol:100mg"))
                .allergies(List.of("nillacilan"))
                .person(person)
                .build();
        when(personRepository.findByFirstNameAndLastName("john", "boyd")).thenReturn(Optional.of(person));
        when(medicalRecordMapper.toMedicalRecord(requestDto, person)).thenReturn(expected);
        // When
        MedicalRecord medicalRecord = sut.createMedicalRecord(requestDto);
        // Then
        assertThat(medicalRecord).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void testCreateMedicalRecord_thenThrowNoSuchPersonException() {
        // Given
        CreateMedicalRecordRequestDto requestDto = new CreateMedicalRecordRequestDto(
                "john",
                "boyd",
                "03/06/1984",
                List.of("aznol:350mg", "hydrapermazol:100mg"),
                List.of("nillacilan")
        );
        when(personRepository.findByFirstNameAndLastName("john", "boyd")).thenReturn(Optional.empty());
        // When
        ThrowingCallable throwingCallable = ()-> sut.createMedicalRecord(requestDto);
        // Then
        assertThatThrownBy(throwingCallable).isInstanceOf(NoSuchPersonException.class);
    }

    @Test
    void testCreateMedicalRecord_thenThrowMedicalRecordAlreadyExistException() {
        // Given
        CreateMedicalRecordRequestDto requestDto = new CreateMedicalRecordRequestDto(
                "john",
                "boyd",
                "03/06/1984",
                List.of("aznol:350mg", "hydrapermazol:100mg"),
                List.of("nillacilan")
        );
        Person person = Person.builder()
                .firstName("john")
                .lastName("boyd")
                .address("1509 Culver St")
                .city("Culver")
                .zip("97451")
                .phone("841-874-6512")
                .email("jaboyd@email.com")
                .build();
        when(personRepository.findByFirstNameAndLastName("john", "boyd")).thenReturn(Optional.of(person));
        when(medicalRecordRepository.findByFirstNameAndLastName("john", "boyd")).thenReturn(Optional.of(new MedicalRecord()));
        // When
        ThrowingCallable throwingCallable = ()-> sut.createMedicalRecord(requestDto);
        // Then
        assertThatThrownBy(throwingCallable).isInstanceOf(MedicalRecordAlreadyExistException.class);
    }

}
