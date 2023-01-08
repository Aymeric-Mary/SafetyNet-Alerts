package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.Person;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Component
public class PersonRepository extends AbstractRepository {

    private List<Person> people;

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

}