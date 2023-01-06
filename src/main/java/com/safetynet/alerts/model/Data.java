package com.safetynet.alerts.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Data {

    private List<Person> persons;

    private List<FireStation> firestations;

    private List<MedicalRecord> medicalrecords;

}