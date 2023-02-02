package com.safetynet.UT.mapper;

import com.safetynet.dto.personInfo.PersonResponseDto;
import com.safetynet.mapper.PersonInfoMapper;
import com.safetynet.mapper.PersonInfoMapperImpl;
import com.safetynet.model.MedicalRecord;
import com.safetynet.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class PersonInfoMapperTest {

    private final PersonInfoMapper sut = new PersonInfoMapperImpl();

    @Test
    void toPersonResponseDtoTest() {
        // Given
        Person john = spy(Person.class);
        john.setFirstName("John");
        john.setLastName("Boyd");
        john.setAddress("1509 Culver St");
        john.setPhone("841-874-6513");
        john.setEmail("jaboyd@email.com");
        john.setMedicalRecord(MedicalRecord.builder()
                .medications(List.of("aznol:350mg", "hydrapermazol:100mg"))
                .allergies(List.of("nillacilan"))
                .build());
        doReturn(36).when(john).getAge();
        Person jacob = spy(Person.class);
        jacob.setFirstName("Jacob");
        jacob.setLastName("Boyd");
        jacob.setAddress("1509 Culver St");
        jacob.setPhone("841-874-6512");
        jacob.setEmail("drk@email.com");
        jacob.setMedicalRecord(MedicalRecord.builder()
                .medications(List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"))
                .allergies(List.of())
                .build());
        doReturn(23).when(jacob).getAge();
        // When
        List<PersonResponseDto> responseDtos = sut.toPersonResponseDtoList(List.of(john, jacob));
        // Then
        List<PersonResponseDto> expectedResponseDtos = List.of(
                PersonResponseDto.builder()
                        .firstName("John")
                        .lastName("Boyd")
                        .address("1509 Culver St")
                        .age(36)
                        .email("jaboyd@email.com")
                        .medications(List.of("aznol:350mg", "hydrapermazol:100mg"))
                        .allergies(List.of("nillacilan"))
                        .build(),
                PersonResponseDto.builder()
                        .firstName("Jacob")
                        .lastName("Boyd")
                        .address("1509 Culver St")
                        .age(23)
                        .email("drk@email.com")
                        .medications(List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"))
                        .allergies(List.of())
                        .build()
        );
        assertThat(responseDtos)
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(expectedResponseDtos);
    }

}
