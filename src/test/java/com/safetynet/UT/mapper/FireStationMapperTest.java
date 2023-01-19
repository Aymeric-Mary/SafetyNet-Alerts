package com.safetynet.UT.mapper;

import com.safetynet.dto.fireStation.PersonResponseDto;
import com.safetynet.mapper.FireStationMapper;
import com.safetynet.mapper.FireStationMapperImpl;
import com.safetynet.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FireStationMapperTest {

    private final FireStationMapper sut = new FireStationMapperImpl();

    @Test
    void toPersonResponseDtoTest() {
        // Given
        Person person = Person.builder()
                .firstName("John")
                .lastName("Boyd")
                .address("1509 Culver St")
                .phone("841-874-6512")
                .build();
        // When
        PersonResponseDto responseDto = sut.toPersonResponseDto(person);
        // Then
        var expectedResponseDto = new PersonResponseDto("John", "Boyd", "1509 Culver St", "841-874-6512");
        assertThat(responseDto).isEqualTo(expectedResponseDto);
    }

    @Test
    void toPersonResponseDtosTest() {
        // Given
        List<Person> people = List.of(
                Person.builder().firstName("John").lastName("Boyd").address("1509 Culver St").phone("841-874-6512").build(),
                Person.builder().firstName("Brian").lastName("Stelzer").address("947 E. Rose Dr").phone("841-874-7784").build()
        );
        // When
        List<PersonResponseDto> responseDtos = sut.toPersonResponseDtos(people);
        // Then
        var expectedResponseDtos = List.of(
                new PersonResponseDto("John", "Boyd", "1509 Culver St", "841-874-6512"),
                new PersonResponseDto("Brian", "Stelzer", "947 E. Rose Dr", "841-874-7784")
        );
        assertThat(responseDtos).isEqualTo(expectedResponseDtos);
    }

}
