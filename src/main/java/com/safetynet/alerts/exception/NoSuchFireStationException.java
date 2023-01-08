package com.safetynet.alerts.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NoSuchFireStationException extends RuntimeException {

    private final Integer station;

}
