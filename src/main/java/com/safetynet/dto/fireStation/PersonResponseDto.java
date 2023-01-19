package com.safetynet.dto.fireStation;

public record PersonResponseDto(
        String firstName,
        String lastName,
        String address,
        String phone
) {
}