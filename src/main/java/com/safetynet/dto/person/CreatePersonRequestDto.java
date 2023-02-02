package com.safetynet.dto.person;

public record CreatePersonRequestDto (
        String firstName,
        String lastName,
        String address,
        String city,
        String zip,
        String phone,
        String email
) {
}
