package com.safetynet.dto.person;

public record UpdatePersonRequestDto(
        String firstName,
        String lastName,
        String address,
        String city,
        String zip,
        String phone,
        String email
) {
}
