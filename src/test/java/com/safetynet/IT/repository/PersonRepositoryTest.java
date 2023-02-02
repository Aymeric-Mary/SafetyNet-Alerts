package com.safetynet.IT.repository;

import com.safetynet.model.FireStation;
import com.safetynet.model.Person;
import com.safetynet.repository.FireStationRepository;
import com.safetynet.repository.PersonRepository;
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
        assertThat(people.size()).isEqualTo(24);
    }

    @ParameterizedTest
    @CsvSource({
            "1,6",
            "2,4",
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