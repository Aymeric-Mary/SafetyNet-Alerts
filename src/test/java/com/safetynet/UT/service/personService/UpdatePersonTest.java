package com.safetynet.UT.service.personService;

import com.safetynet.dto.medicalRecord.UpdateMedicalRecordRequestDto;
import com.safetynet.dto.person.UpdatePersonRequestDto;
import com.safetynet.exception.NoSuchMedicalRecordException;
import com.safetynet.exception.NoSuchPersonException;
import com.safetynet.mapper.MedicalRecordMapper;
import com.safetynet.mapper.PersonMapper;
import com.safetynet.model.MedicalRecord;
import com.safetynet.model.Person;
import com.safetynet.repository.MedicalRecordRepository;
import com.safetynet.repository.PersonRepository;
import com.safetynet.service.MedicalRecordService;
import com.safetynet.service.PersonService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdatePersonTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonService sut;

    @Test
    void testUpdatePerson() {
        // Given
        UpdatePersonRequestDto requestDto = new UpdatePersonRequestDto(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "new-email@email.com"
        );
        Person person = Person.builder()
                .firstName("John")
                .lastName("Boyd")
                .address("1509 Culver St")
                .city("Culver")
                .zip("97451")
                .phone("841-874-6512")
                .email("new-email@email.com")
                .build();
        when(personRepository.findByFirstNameAndLastName("John", "Boyd")).thenReturn(Optional.of(person));
        // When
        Person result = sut.updatePerson(requestDto);
        // Then
        assertThat(result).usingRecursiveComparison().isEqualTo(person);
    }

    @Test
    void testUpdatePersonWithNoPerson() {
        // Given
        UpdatePersonRequestDto requestDto = new UpdatePersonRequestDto(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "new-email@email.com");
        when(personRepository.findByFirstNameAndLastName("John", "Boyd")).thenReturn(Optional.empty());
        // When
        ThrowingCallable throwingCallable = () -> sut.updatePerson(requestDto);
        // Then
        assertThatThrownBy(throwingCallable).isInstanceOf(NoSuchPersonException.class);
    }
}
