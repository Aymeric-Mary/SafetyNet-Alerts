package com.safetynet.model;

import lombok.*;
import lombok.Data;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecord {

    private String firstName;

    private String lastName;

    private String birthdate;

    private List<String> medications;

    private List<String> allergies;

    private Person person;
}
