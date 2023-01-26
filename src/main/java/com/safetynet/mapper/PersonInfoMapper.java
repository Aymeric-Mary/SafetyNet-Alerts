package com.safetynet.mapper;

import com.safetynet.dto.personInfo.PersonResponseDto;
import com.safetynet.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PersonInfoMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "age", source = "age")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "medications", source = "medicalRecord.medications")
    @Mapping(target = "allergies", source = "medicalRecord.allergies")
    PersonResponseDto toPersonResponseDto(Person person);

    List<PersonResponseDto> toPersonResponseDtoList(List<Person> people);

}
