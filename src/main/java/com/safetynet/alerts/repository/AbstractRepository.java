package com.safetynet.alerts.repository;

import com.google.gson.Gson;
import com.safetynet.alerts.model.Data;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@Repository
public abstract class AbstractRepository {

    protected Data data;

    AbstractRepository() {
        try {
            Gson gson = new Gson();
            Path path = new ClassPathResource("data/data.json").getFile().toPath();
            String content = Files.readString(path);
            this.data = gson.fromJson(content, Data.class);
            this.data.getPeople().forEach(this::mapPersonRelation);
            this.data.getMedicalrecords().forEach(this::mapPersonToMedicalRecord);
            this.data.getFirestations().forEach(this::mapPersonToFireStation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mapPersonRelation(Person person) {
        mapMedicalRecordToPerson(person);
        mapFireStationToPerson(person);
    }

    private void mapMedicalRecordToPerson(Person person) {
        this.data.getMedicalrecords()
                .stream()
                .filter(medicalRecord -> medicalRecord.getFirstName().equals(person.getFirstName()) && medicalRecord.getLastName().equals(person.getLastName()))
                .findFirst()
                .ifPresent(person::setMedicalRecord);
    }

    private void mapFireStationToPerson(Person person) {
        this.data.getFirestations()
                .stream()
                .filter(fireStation -> fireStation.getAddress().equals(person.getAddress()))
                .findFirst()
                .ifPresent(person::setFireStation);
    }

    private void mapPersonToMedicalRecord(MedicalRecord medicalRecord) {
        this.data.getPeople()
                .stream()
                .filter(person -> person.getMedicalRecord().equals(medicalRecord))
                .findFirst()
                .ifPresent(medicalRecord::setPerson);
    }

    private void mapPersonToFireStation(FireStation fireStation) {
        List<Person> people = this.data.getPeople()
                .stream()
                .filter(person -> person.getFireStation().equals(fireStation))
                .toList();
        fireStation.setPeople(people);
    }

}
