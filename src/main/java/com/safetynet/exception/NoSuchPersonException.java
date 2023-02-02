package com.safetynet.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NoSuchPersonException extends RuntimeException {
    private String firstName;
    private String lastName;
}
