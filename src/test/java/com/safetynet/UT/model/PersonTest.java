package com.safetynet.UT.model;

import com.safetynet.model.MedicalRecord;
import com.safetynet.model.Person;
import com.safetynet.utils.Utils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonTest {

    @ParameterizedTest
    @CsvSource({
            "01/07/2013,10",
            "01/08/2013,9"
    })
    public void getAge_withLimitDate(String birthDate, Integer expectedAge) {
        // Given
        MedicalRecord medicalRecord = MedicalRecord.builder()
                .birthdate(birthDate)
                .build();
        Person person = Person.builder()
                .medicalRecord(medicalRecord)
                .build();
        try (MockedStatic<Utils> utilities = Mockito.mockStatic(Utils.class)) {
            utilities.when(Utils::getCurrentDate).thenReturn(LocalDate.parse("2023-01-07"));
            // When
            Integer age = person.getAge();
            // Then
            assertThat(age).isEqualTo(expectedAge);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "18,false",
            "19,true"
    })
    void isAdult_withLimitAge(Integer age, Boolean expectedIsAdult) {
        // Given
        Person personMock = mock(Person.class);
        when(personMock.getAge()).thenReturn(age);
        when(personMock.isAdult()).thenCallRealMethod();
        // When
        Boolean isAdult = personMock.isAdult();
        // Then
        assertThat(isAdult).isEqualTo(expectedIsAdult);
    }

    @ParameterizedTest
    @CsvSource({
            "18,true",
            "19,false"
    })
    void isChild_withLimitAge(Integer age, Boolean expectedIsChild) {
        // Given
        Person personMock = mock(Person.class);
        when(personMock.getAge()).thenReturn(age);
        when(personMock.isChild()).thenCallRealMethod();
        // When
        Boolean isAdult = personMock.isChild();
        // Then
        assertThat(isAdult).isEqualTo(expectedIsChild);
    }
}
