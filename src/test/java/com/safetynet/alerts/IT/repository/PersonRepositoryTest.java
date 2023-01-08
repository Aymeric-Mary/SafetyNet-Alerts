package com.safetynet.alerts.IT.repository;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.FireStationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class PersonRepositoryTest{

    private final PersonRepository sut = new PersonRepository();

    private final FireStationRepository fireStationRepository = new FireStationRepository();

    @Test
    void findAllTest() {
        // Given
        // When
        List<Person> people = sut.findAll();
        // Then
        assertThat(people.size()).isEqualTo(23);
        assertThat(people)
                .flatExtracting("firstName", "lastName", "address", "city", "zip", "phone", "email", "medicalRecord", "fireStation")
                .doesNotContainNull();
    }

    @ParameterizedTest
    @CsvSource({
            "1,6",
            "2,5",
            "3,11",
            "4,1",
            "5,0",
    })
    void findByFireStationsTest(Integer station, Integer expectedSize) {
        // Given
        List<FireStation> fireStations = fireStationRepository.findByStation(station);
        // When
        List<Person> persons = sut.findByFireStations(fireStations);
        // Then
        assertThat(persons.size()).isEqualTo(expectedSize);
        assertThat(persons).allMatch(person -> fireStations.contains(person.getFireStation()));
    }

}