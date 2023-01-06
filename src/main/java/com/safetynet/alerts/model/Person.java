package com.safetynet.alerts.model;

import lombok.*;
import lombok.Data;

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
}