package com.safetynet.dto.medicalRecord;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class MedicalRecordResponseDto {
    private String firstName;

    private String lastName;

    private String birthdate;

    private List<String> medications;

    private List<String> allergies;
}
