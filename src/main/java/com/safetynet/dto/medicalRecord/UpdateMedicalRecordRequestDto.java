package com.safetynet.dto.medicalRecord;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class UpdateMedicalRecordRequestDto {
    private String firstName;

    private String lastName;

    private String birthdate;

    private List<String> medications;

    private List<String> allergies;
}
