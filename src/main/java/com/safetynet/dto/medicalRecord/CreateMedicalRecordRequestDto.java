package com.safetynet.dto.medicalRecord;

import java.util.List;

public record CreateMedicalRecordRequestDto (
        String firstName,
        String lastName,
        String birthdate,
        List<String> medications,
        List<String> allergies
) {
}
