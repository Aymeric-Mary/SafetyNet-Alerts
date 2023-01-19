package com.safetynet.UT.mapper;

import com.safetynet.dto.fire.PersonResponseDto;
import com.safetynet.mapper.FireMapper;
import com.safetynet.mapper.FireMapperImpl;
import com.safetynet.model.MedicalRecord;
import com.safetynet.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class FireMapperTest {

    private final FireMapper sut = new FireMapperImpl();

    @Test
    void toPersonResponseDtosTest() {
        // Given
        Person john = spy(Person.class);
        john.setFirstName("John");
        john.setLastName("Boyd");
        john.setPhone("841-874-6512");
        john.setMedicalRecord(MedicalRecord.builder()
                .medications(List.of("aznol:350mg", "hydrapermazol:100mg"))
                .allergies(List.of("peanut", "shellfish", "aznol"))
                .build());
        doReturn(36).when(john).getAge();
        Person jacob = spy(Person.class);
        jacob.setFirstName("Jacob");
        jacob.setLastName("Boyd");
        jacob.setPhone("841-874-6513");
        jacob.setMedicalRecord(MedicalRecord.builder()
                .medications(List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"))
                .allergies(List.of())
                .build());
        doReturn(15).when(jacob).getAge();
        List<Person> people = List.of(john, jacob);
        // When
        List<PersonResponseDto> personResponseDtos = sut.toPersonResponseDtos(people);
        // Then
        var expectedResponseDto = List.of(
                new PersonResponseDto("John", "Boyd", "841-874-6512", 36, List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("peanut", "shellfish", "aznol")),
                new PersonResponseDto("Jacob", "Boyd", "841-874-6513", 15, List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"), List.of())
        );

        assertThat(personResponseDtos).isEqualTo(expectedResponseDto);
    }

}
