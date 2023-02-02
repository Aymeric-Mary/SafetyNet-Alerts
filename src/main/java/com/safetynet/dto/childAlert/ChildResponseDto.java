package com.safetynet.dto.childAlert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
public class ChildResponseDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private List<PersonResponseDto> otherMembers;
}
