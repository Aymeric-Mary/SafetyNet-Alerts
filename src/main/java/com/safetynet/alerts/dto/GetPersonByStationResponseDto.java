package com.safetynet.alerts.dto;

public record GetPersonByStationResponseDto(
        String firstName,
        String lastName,
        String address,
        String phone
) {
}