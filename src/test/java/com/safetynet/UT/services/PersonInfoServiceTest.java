package com.safetynet.UT.services;

import com.safetynet.dto.personInfo.PersonInfoResponseDto;
import com.safetynet.dto.personInfo.PersonResponseDto;
import com.safetynet.model.MedicalRecord;
import com.safetynet.model.Person;
import com.safetynet.repository.PersonRepository;
import com.safetynet.mapper.PersonInfoMapper;
import com.safetynet.service.PersonInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonInfoServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonInfoMapper personInfoMapper;

    @InjectMocks
    private PersonInfoService sut;

    @Test
    void testGetPersonInfoResponseDto_withoutFirstNameAndLastName() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> sut.getPersonInfoResponseDto(null, null));
    }

    @Test
    void testGetPersonInfoResponseDto_withFirstNameAndLastName() {
        // Given
        List<Person> people = List.of(
                Person.builder().firstName("John").lastName("Boyd").build()
        );
        PersonInfoResponseDto expected = new PersonInfoResponseDto(
                List.of(
                        PersonResponseDto.builder().firstName("John").lastName("Boyd").build()
                )
        );
        when(personRepository.findByFirstNameAndLastName("John", "Boyd")).thenReturn(people);
        when(personInfoMapper.toPersonResponseDtoList(people)).thenReturn(expected.getPeople());
        // When
        PersonInfoResponseDto result = sut.getPersonInfoResponseDto("John", "Boyd");
        // Then
        verify(personRepository).findByFirstNameAndLastName("John", "Boyd");
        verify(personInfoMapper).toPersonResponseDtoList(people);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGetPersonInfoResponseDto_withLastName() {
        // Given
        List<Person> people = List.of(
                Person.builder().firstName("John").lastName("Boyd").build(),
                Person.builder().firstName("Jacob").lastName("Boyd").build()
        );
        PersonInfoResponseDto expected = new PersonInfoResponseDto(
                List.of(
                        PersonResponseDto.builder().firstName("John").lastName("Boyd").build(),
                        PersonResponseDto.builder().firstName("Jacob").lastName("Boyd").build()
                )
        );
        when(personRepository.findByLastName("Boyd")).thenReturn(people);
        when(personInfoMapper.toPersonResponseDtoList(people)).thenReturn(expected.getPeople());
        // When
        PersonInfoResponseDto result = sut.getPersonInfoResponseDto(null, "Boyd");
        // Then
        verify(personRepository).findByLastName("Boyd");
        verify(personInfoMapper).toPersonResponseDtoList(people);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGetPersonInfoResponseDto_withFirstName() {
        // Given
        List<Person> people = List.of(
                Person.builder().firstName("John").lastName("Boyd").build(),
                Person.builder().firstName("John").lastName("Doe").build()
        );
        PersonInfoResponseDto expected = new PersonInfoResponseDto(
                List.of(
                        PersonResponseDto.builder().firstName("John").lastName("Boyd").build(),
                        PersonResponseDto.builder().firstName("John").lastName("Doe").build()
                )
        );
        when(personRepository.findByFirstName("John")).thenReturn(people);
        when(personInfoMapper.toPersonResponseDtoList(people)).thenReturn(expected.getPeople());
        // When
        PersonInfoResponseDto result = sut.getPersonInfoResponseDto("John", null);
        // Then
        verify(personRepository).findByFirstName("John");
        verify(personInfoMapper).toPersonResponseDtoList(people);
        assertThat(result).isEqualTo(expected);
    }

}
