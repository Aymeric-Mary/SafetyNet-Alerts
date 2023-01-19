package com.safetynet.repository;

import com.safetynet.model.FireStation;
import com.safetynet.model.Person;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

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
                .filter(person -> person.getAddress().equals(address))
                .toList();
    }

}