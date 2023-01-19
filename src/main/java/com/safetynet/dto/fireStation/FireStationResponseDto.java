package com.safetynet.dto.fireStation;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class FireStationResponseDto {

    private List<PersonResponseDto> people;

    private Integer nbAdults;

    private Integer nbChildren;

}
