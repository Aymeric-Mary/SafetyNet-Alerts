package com.safetynet.alerts.repository;

import com.safetynet.alerts.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonRepository extends AbstractRepository {

    private List<Person> persons;

    public PersonRepository(){
        this.persons = data.getPersons();
    }

    public List<Person> findAll() {
        return persons;
    }

}