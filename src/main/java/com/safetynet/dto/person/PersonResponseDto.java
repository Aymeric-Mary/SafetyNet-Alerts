package com.safetynet.dto.person;

public record PersonResponseDto(
        String firstName,
        String lastName,
        String address,
        String city,
        String zip,
        String phone,
        String email
) {
}
