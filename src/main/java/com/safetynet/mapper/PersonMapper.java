package com.safetynet.mapper;

import com.safetynet.dto.medicalRecord.CreateMedicalRecordRequestDto;
import com.safetynet.dto.medicalRecord.MedicalRecordResponseDto;
import com.safetynet.dto.medicalRecord.UpdateMedicalRecordRequestDto;
import com.safetynet.dto.person.CreatePersonRequestDto;
import com.safetynet.dto.person.PersonResponseDto;
import com.safetynet.dto.person.UpdatePersonRequestDto;
import com.safetynet.model.MedicalRecord;
import com.safetynet.model.Person;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface PersonMapper {

    @Mapping(target = "medicalRecord", ignore = true)
    @Mapping(target = "fireStation", ignore = true)
    Person toPerson(CreatePersonRequestDto requestDto);

    @Mapping(target = "medicalRecord", ignore = true)
    @Mapping(target = "fireStation", ignore = true)
    void mapUpdateRequestDto(@MappingTarget Person person, UpdatePersonRequestDto requestDto);

    PersonResponseDto toResponseDto(Person person);
}