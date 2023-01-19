package com.safetynet.dto.fire;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FireResponseDto {

    List<PersonResponseDto> people;

    Integer fireStationNumber;

}
