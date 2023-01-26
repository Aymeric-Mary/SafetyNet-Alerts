package com.safetynet.mapper;

import com.safetynet.dto.floodStations.PersonResponseDto;
import com.safetynet.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface FloodStationsMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "age", source = "age")
    @Mapping(target = "medications", source = "medicalRecord.medications")
    @Mapping(target = "allergies", source = "medicalRecord.allergies")
    PersonResponseDto toPersonResponseDto(Person person);
}
