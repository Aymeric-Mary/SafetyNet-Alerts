package com.safetynet.dto;

public record GetPersonByStationResponseDto(
        String firstName,
        String lastName,
        String address,
        String phone
) {
}