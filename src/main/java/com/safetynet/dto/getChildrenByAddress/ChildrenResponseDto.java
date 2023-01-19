package com.safetynet.dto.getChildrenByAddress;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChildrenResponseDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private List<PersonResponseDto> otherMembers;
}
