package com.safetynet.dto.personInfo;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class PersonInfoResponseDto {

    List<PersonResponseDto> people;

}
