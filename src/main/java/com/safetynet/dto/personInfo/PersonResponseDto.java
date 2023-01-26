package com.safetynet.dto.personInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class PersonResponseDto {

    private String firstName;

    private String lastName;

    private String address;

    private Integer age;

    private String email;

    private List<String> medications;

    private List<String> allergies;
}
