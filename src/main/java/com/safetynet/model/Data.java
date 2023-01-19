package com.safetynet.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Data {

    private List<Person> people;

    private List<FireStation> firestations;

    private List<MedicalRecord> medicalrecords;

}