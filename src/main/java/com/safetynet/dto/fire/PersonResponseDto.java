package com.safetynet.dto.fire;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Data
public class PersonResponseDto {

    private String firstName;

    private String lastName;

    private String phone;

    private Integer age;

    private List<String> medications;

    private List<String> allergies;

}
