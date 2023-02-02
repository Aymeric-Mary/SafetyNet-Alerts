package com.safetynet.mapper;

import com.safetynet.dto.medicalRecord.MedicalRecordResponseDto;
import com.safetynet.dto.medicalRecord.UpdateMedicalRecordRequestDto;
import com.safetynet.dto.medicalRecord.CreateMedicalRecordRequestDto;
import com.safetynet.model.MedicalRecord;
import com.safetynet.model.Person;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface MedicalRecordMapper {

    @Mapping(target = "firstName", source = "requestDto.firstName")
    @Mapping(target = "lastName", source = "requestDto.lastName")
    @Mapping(target = "birthdate", source = "requestDto.birthdate")
    @Mapping(target = "medications", source = "requestDto.medications")
    @Mapping(target = "allergies", source = "requestDto.allergies")
    MedicalRecord toMedicalRecord(CreateMedicalRecordRequestDto requestDto, Person person);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "birthdate", source = "birthdate")
    @Mapping(target = "medications", source = "medications")
    @Mapping(target = "allergies", source = "allergies")
    void mapUpdateRequestDto(@MappingTarget MedicalRecord medicalRecord, UpdateMedicalRecordRequestDto requestDto);

    MedicalRecordResponseDto toResponseDto(MedicalRecord medicalRecord);
}
