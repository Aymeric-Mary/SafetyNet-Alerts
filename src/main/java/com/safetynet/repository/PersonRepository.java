package com.safetynet.repository;

import com.safetynet.model.FireStation;
import com.safetynet.model.Person;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class PersonRepository extends AbstractRepository {

    private final List<Person> people;

    public PersonRepository() {
        this.people = data.getPeople();
    }

    public List<Person> findAll() {
        return people;
    }

    public List<Person> findByFireStations(List<FireStation> fireStations) {
        return fireStations.stream()
                .map(FireStation::getPeople)
                .flatMap(Collection::stream)
                .toList();
    }

    public List<Person> findByAddress(String address) {
        return people.stream()
                .filter(person -> address.equals(person.getAddress()))
                .toList();
    }

    public List<Person> findByCity(String city) {
        return people.stream()
                .filter(person -> city.equals(person.getCity()))
                .toList();
    }

    public Optional<Person> findByFirstNameAndLastName(String firstName, String lastName) {
        return people.stream()
                .filter(person -> firstName.equals(person.getFirstName()) && lastName.equals(person.getLastName()))
                .findFirst();
    }

    public List<Person> findByLastName(String lastName) {
        return people.stream()
                .filter(person -> lastName.equals(person.getLastName()))
                .toList();
    }

    public List<Person> findByFirstName(String firstName) {
        return people.stream()
                .filter(person -> firstName.equals(person.getFirstName()))
                .toList();
    }
}