package com.safetynet.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MedicalRecordAlreadyExistException extends RuntimeException {
    private String firstName;
    private String lastName;
}
