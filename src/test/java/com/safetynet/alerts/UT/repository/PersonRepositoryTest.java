package com.safetynet.alerts.UT.repository;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class PersonRepositoryTest {

    private final PersonRepository sut = new PersonRepository();

    private final FireStationRepository fireStationRepository = new FireStationRepository();

    @Test
    void findAllTest() {
        // Given
        // When
        List<Person> persons = sut.findAll();
        // Then
        assertThat(persons.size()).isEqualTo(23);
        assertThat(persons)
                .flatExtracting("firstName", "lastName", "address", "city", "zip", "phone", "email", "medicalRecord", "fireStation")
                .doesNotContainNull();
    }

}