package com.safetynet.alerts.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GetPeopleByStationResponseDto {

    private List<GetPersonByStationResponseDto> people;

    private Integer nbAdults;

    private Integer nbChildren;

}
