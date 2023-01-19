package com.safetynet.exception;

import lombok.Getter;

@Getter
public class NoSuchFireStationException extends RuntimeException {

    private Integer station;

    private String address;

    public NoSuchFireStationException(Integer station) {
        this.station = station;
    }

    public NoSuchFireStationException(String address) {
        this.address = address;
    }

}
