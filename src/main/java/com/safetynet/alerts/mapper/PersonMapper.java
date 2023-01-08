package com.safetynet.alerts.mapper;

import com.safetynet.alerts.dto.GetPersonByStationResponseDto;
import com.safetynet.alerts.model.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PersonMapper {

    GetPersonByStationResponseDto toGetPersonByStationResponseDto(Person person);

    List<GetPersonByStationResponseDto> toGetPersonByStationResponseDtos(List<Person> people);

}