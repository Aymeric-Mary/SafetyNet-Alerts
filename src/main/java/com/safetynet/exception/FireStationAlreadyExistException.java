package com.safetynet.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FireStationAlreadyExistException extends RuntimeException {

    private String address;
}
