package com.safetynet.model;

import com.safetynet.utils.Utils;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String zip;

    private String phone;

    private String email;

    private MedicalRecord medicalRecord;

    private FireStation fireStation;

    public Integer getAge() {
        LocalDate birthDate = LocalDate.parse(this.medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return Period.between(birthDate, Utils.getCurrentDate()).getYears();
    }

    public Boolean isAdult() {
        return getAge() > 18;
    }

    public Boolean isChild() {
        return getAge() <= 18;
    }

}