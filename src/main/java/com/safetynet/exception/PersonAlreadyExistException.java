package com.safetynet.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PersonAlreadyExistException extends RuntimeException {

    private String firstName;
    private String lastName;
}
