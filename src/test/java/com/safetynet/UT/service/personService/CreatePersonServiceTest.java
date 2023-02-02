package com.safetynet.UT.service.personService;

import com.safetynet.dto.person.CreatePersonRequestDto;
import com.safetynet.exception.PersonAlreadyExistException;
import com.safetynet.mapper.PersonMapper;
import com.safetynet.model.Person;
import com.safetynet.repository.PersonRepository;
import com.safetynet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class CreatePersonServiceTest {

    @Mock
    private PersonMapper personMapper;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService sut;

    @Test
    void testCreatePerson() {
        // Given
        CreatePersonRequestDto requestDto = new CreatePersonRequestDto(
                "John",
                "Boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com");
        Person expected = Person.builder()
                .firstName("John")
                .lastName("Boyd")
                .address("1509 Culver St")
                .city("Culver")
                .zip("97451")
                .phone("841-874-6512")
                .email("jaboyd@email.com")
                .build();
        when(personRepository.findByFirstNameAndLastName("John", "Boyd")).thenReturn(Optional.empty());
        when(personMapper.toPerson(requestDto)).thenReturn(expected);
        // When
        Person person = sut.createPerson(requestDto);
        // Then
        assertThat(person).isEqualTo(expected);
    }

    @Test
    void testCreatePerson_thenThrowPersonAlreadyExistException() {
        // Given
        CreatePersonRequestDto requestDto = new CreatePersonRequestDto(
                "john",
                "boyd",
                "1509 Culver St",
                "Culver",
                "97451",
                "841-874-6512",
                "jaboyd@email.com");
        when(personRepository.findByFirstNameAndLastName("john", "boyd")).thenReturn(Optional.of(new Person()));
        // When
        ThrowingCallable createPerson = () -> sut.createPerson(requestDto);
        // Then
        assertThatThrownBy(createPerson).isInstanceOf(PersonAlreadyExistException.class);
    }

}
