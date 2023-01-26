package com.safetynet.dto.personInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class PersonInfoResponseDto {

    List<PersonResponseDto> people;

}
