package com.safetynet.UT.mapper;

import com.safetynet.dto.childAlert.ChildResponseDto;
import com.safetynet.dto.childAlert.PersonResponseDto;
import com.safetynet.mapper.ChildAlertMapper;
import com.safetynet.mapper.ChildAlertMapperImpl;
import com.safetynet.model.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChildAlertMapperTest {

    private final ChildAlertMapper sut = new ChildAlertMapperImpl();

    @Test
    void toPersonResponseDtoTest() {

        // Given
        Person person = mock(Person.class);
        when(person.getFirstName()).thenReturn("John");
        when(person.getLastName()).thenReturn("Boyd");
        when(person.getAge()).thenReturn(25);
        // When
        PersonResponseDto responseDto = sut.toPersonResponseDto(person);
        // Then
        var expectedResponseDto = new PersonResponseDto("John", "Boyd");
        assertThat(responseDto).isEqualTo(expectedResponseDto);
    }

    @Test
    void toChildrenResponseDtosTest() {
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
        List<ChildResponseDto> responseDto = sut.toChildrenResponseDtos(List.of(john, jacob, tenley, roger));
        // Then
        var expectedResponseDto = List.of(
                new ChildResponseDto("Tenley", "Boyd", 10,
                        List.of(
                                new com.safetynet.dto.childAlert.PersonResponseDto("John", "Boyd"),
                                new com.safetynet.dto.childAlert.PersonResponseDto("Jacob", "Boyd"),
                                new com.safetynet.dto.childAlert.PersonResponseDto("Roger", "Boyd")
                        )
                ),
                new ChildResponseDto("Roger", "Boyd", 5,
                        List.of(
                                new com.safetynet.dto.childAlert.PersonResponseDto("John", "Boyd"),
                                new com.safetynet.dto.childAlert.PersonResponseDto("Jacob", "Boyd"),
                                new com.safetynet.dto.childAlert.PersonResponseDto("Tenley", "Boyd")
                        )
                )
        );
        assertThat(responseDto).isEqualTo(expectedResponseDto);
    }

    @Test
    void toChildrenResponseDtoTest() {
        // Given
        Person person = mock(Person.class);
        when(person.getFirstName()).thenReturn("Tenley");
        when(person.getLastName()).thenReturn("Boyd");
        when(person.getAge()).thenReturn(10);
        // When
        ChildResponseDto responseDto = sut.toChildrenResponseDto(person);
        // Then
        var expectedResponseDto = new ChildResponseDto("Tenley", "Boyd", 10, null);
        assertThat(responseDto).isEqualTo(expectedResponseDto);
    }

}
