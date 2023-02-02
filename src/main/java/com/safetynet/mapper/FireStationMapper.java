package com.safetynet.mapper;

import com.safetynet.dto.fireStation.CreateFireStationRequestDto;
import com.safetynet.dto.fireStation.FireStationResponseDto;
import com.safetynet.dto.fireStation.PersonResponseDto;
import com.safetynet.model.FireStation;
import com.safetynet.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface FireStationMapper {

    List<PersonResponseDto> toPersonResponseDtos(List<Person> people);

    PersonResponseDto toPersonResponseDto(Person person);

    @Mapping(target = "people", ignore = true)
    FireStation toFireStation(CreateFireStationRequestDto createFireStationRequestDto);

    FireStationResponseDto toResponseDto(FireStation fireStation);
}
