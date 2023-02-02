package com.safetynet.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NoSuchMedicalRecordException extends RuntimeException {

    private final String firstName;
    private final String lastName;
}
