package com.safetynet.UT.mapper;

import com.safetynet.dto.floodStations.PersonResponseDto;
import com.safetynet.mapper.FloodStationsMapper;
import com.safetynet.mapper.FloodStationsMapperImpl;
import com.safetynet.model.MedicalRecord;
import com.safetynet.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class FloodStationsMapperTest {

    private final FloodStationsMapper sut = new FloodStationsMapperImpl();

    @Test
    void toPersonResponseDtoTest() {
        // Given
        MedicalRecord medicalRecord = MedicalRecord.builder()
                .medications(List.of("aznol:350mg", "hydrapermazol:100mg"))
                .allergies(List.of("nillacilan"))
                .build();
        Person john = spy(Person.class);
        john.setFirstName("John");
        john.setLastName("Boyd");
        john.setAddress("1509 Culver St");
        john.setPhone("841-874-6512");
        john.setMedicalRecord(medicalRecord);
        doReturn(36).when(john).getAge();
        // When
        PersonResponseDto responseDto = sut.toPersonResponseDto(john);
        // Then
        var expectedResponseDto = new PersonResponseDto(
                "John",
                "Boyd",
                "841-874-6512",
                36,
                List.of("aznol:350mg", "hydrapermazol:100mg"),
                List.of("nillacilan")
        );
        assertThat(responseDto).isEqualTo(expectedResponseDto);
    }

}
