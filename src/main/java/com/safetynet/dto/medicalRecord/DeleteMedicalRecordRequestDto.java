package com.safetynet.dto.medicalRecord;

import lombok.Getter;

public record DeleteMedicalRecordRequestDto (
        String firstName,
        String lastName
) {
}
