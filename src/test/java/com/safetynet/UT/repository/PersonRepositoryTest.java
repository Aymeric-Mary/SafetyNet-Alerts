package com.safetynet.UT.repository;

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

    @Test
    void findByAddressTest(){
        // Given
        String address = "1509 Culver St";
        // When
        List<Person> persons = sut.findByAddress(address);
        // Then
        assertThat(persons.size()).isEqualTo(5);
        assertThat(persons).allMatch(person -> person.getAddress().equals(address));
    }

    @Test
    void findByCityTest(){
        // Given
        String city = "Culver";
        // When
        List<Person> persons = sut.findByCity(city);
        // Then
        assertThat(persons.size()).isEqualTo(24);
        assertThat(persons).allMatch(person -> person.getCity().equals(city));
    }

    @Test
    void findByFirstNameAndLastNameTest(){
        // Given
        String firstName = "John";
        String lastName = "Boyd";
        // When
        Person person = sut.findByFirstNameAndLastName(firstName, lastName).get();
        // Then
        assertThat(person.getFirstName()).isEqualTo(firstName);
        assertThat(person.getLastName()).isEqualTo(lastName);
    }

    @Test
    void findByLastNameTest(){
        // Given
        String lastName = "Boyd";
        // When
        List<Person> persons = sut.findByLastName(lastName);
        // Then
        assertThat(persons.size()).isEqualTo(6);
        assertThat(persons).allMatch(person -> person.getLastName().equals(lastName));
    }

    @Test
    void findByFirstNameTest(){
        // Given
        String firstName = "John";
        // When
        List<Person> persons = sut.findByFirstName(firstName);
        // Then
        assertThat(persons.size()).isEqualTo(1);
        assertThat(persons).allMatch(person -> person.getFirstName().equals(firstName));
    }

    @Test
    void saveTest(){
        // Given
        Person person = Person.builder()
                .firstName("John")
                .lastName("Doe")
                .address("1234 Main St")
                .city("Culver")
                .zip("97451")
                .phone("123-456-7890")
                .email("jaboyd@email.com")
                .build();
        // When
        Person result = sut.save(person);
        // Then
        List<Person> persons = sut.findAll();
        assertThat(persons)
                .usingRecursiveFieldByFieldElementComparator()
                .contains(person);
        assertThat(result).isEqualTo(person);
    }

}