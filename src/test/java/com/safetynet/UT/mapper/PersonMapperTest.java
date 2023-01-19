package com.safetynet.UT.mapper;

import com.safetynet.dto.GetPersonByStationResponseDto;
import com.safetynet.dto.getChildrenByAddress.ChildrenResponseDto;
import com.safetynet.dto.getChildrenByAddress.PersonResponseDto;
import com.safetynet.mapper.PersonMapper;
import com.safetynet.mapper.PersonMapperImpl;
import com.safetynet.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonMapperTest {

    private final PersonMapper personMapper = new PersonMapperImpl();

    @Test
    void toGetPersonByStationResponseDto() {
        // Given
        Person person = Person.builder()
                .firstName("John")
                .lastName("Boyd")
                .address("1509 Culver St")
                .phone("841-874-6512")
                .build();
        // When
        GetPersonByStationResponseDto responseDto = personMapper.toGetPersonByStationResponseDto(person);
        // Then
        var expectedResponseDto = new GetPersonByStationResponseDto("John", "Boyd", "1509 Culver St", "841-874-6512");
        assertThat(responseDto).isEqualTo(expectedResponseDto);
    }

    @Test
    void toGetPersonByStationResponseDtos() {
        // Given
        List<Person> people = List.of(
                Person.builder().firstName("John").lastName("Boyd").address("1509 Culver St").phone("841-874-6512").build(),
                Person.builder().firstName("Brian").lastName("Stelzer").address("947 E. Rose Dr").phone("841-874-7784").build()
        );
        // When
        List<GetPersonByStationResponseDto> responseDtos = personMapper.toGetPersonByStationResponseDtos(people);
        // Then
        var expectedResponseDtos = List.of(
                new GetPersonByStationResponseDto("John", "Boyd", "1509 Culver St", "841-874-6512"),
                new GetPersonByStationResponseDto("Brian", "Stelzer", "947 E. Rose Dr", "841-874-7784")
        );
        assertThat(responseDtos).isEqualTo(expectedResponseDtos);
    }

    @Test
    void toPersonResponseDto() {
        // Given
        Person person = mock(Person.class);
        when(person.getFirstName()).thenReturn("John");
        when(person.getLastName()).thenReturn("Boyd");
        when(person.getAge()).thenReturn(25);
        // When
        PersonResponseDto responseDto = personMapper.toPersonResponseDto(person);
        // Then
        var expectedResponseDto = new PersonResponseDto("John", "Boyd");
        assertThat(responseDto).isEqualTo(expectedResponseDto);
    }

    @Test
    void toChildrenResponseDtos() {
        // Given
        Person john = mock(Person.class);
        Person tenley = mock(Person.class);
        Person jacob = mock(Person.class);
        Person roger = mock(Person.class);
        when(john.getFirstName()).thenReturn("John");
        when(john.getLastName()).thenReturn("Boyd");
        when(jacob.getFirstName()).thenReturn("Jacob");
        when(jacob.getLastName()).thenReturn("Boyd");
        when(tenley.getFirstName()).thenReturn("Tenley");
        when(tenley.getLastName()).thenReturn("Boyd");
        when(tenley.getAge()).thenReturn(10);
        when(tenley.isChild()).thenReturn(true);
        when(roger.getFirstName()).thenReturn("Roger");
        when(roger.getLastName()).thenReturn("Boyd");
        when(roger.getAge()).thenReturn(5);
        when(roger.isChild()).thenReturn(true);
        // When
        List<ChildrenResponseDto> responseDto = personMapper.toChildrenResponseDtos(List.of(john, jacob, tenley, roger));
        // Then
        var expectedResponseDto = List.of(
                new ChildrenResponseDto("Tenley", "Boyd", 10,
                        List.of(
                                new PersonResponseDto("John", "Boyd"),
                                new PersonResponseDto("Jacob", "Boyd"),
                                new PersonResponseDto("Roger", "Boyd")
                        )
                ),
                new ChildrenResponseDto("Roger", "Boyd", 5,
                        List.of(
                                new PersonResponseDto("John", "Boyd"),
                                new PersonResponseDto("Jacob", "Boyd"),
                                new PersonResponseDto("Tenley", "Boyd")
                        )
                )
        );
        assertThat(responseDto).isEqualTo(expectedResponseDto);
    }

    @Test
    void toChildrenResponseDto() {
        // Given
        Person person = mock(Person.class);
        when(person.getFirstName()).thenReturn("Tenley");
        when(person.getLastName()).thenReturn("Boyd");
        when(person.getAge()).thenReturn(10);
        // When
        ChildrenResponseDto responseDto = personMapper.toChildrenResponseDto(person);
        // Then
        var expectedResponseDto = new ChildrenResponseDto("Tenley", "Boyd", 10, null);
        assertThat(responseDto).isEqualTo(expectedResponseDto);
    }

}
