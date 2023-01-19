package com.safetynet.mapper;

import com.safetynet.dto.GetPersonByStationResponseDto;
import com.safetynet.dto.getChildrenByAddress.ChildrenResponseDto;
import com.safetynet.dto.getChildrenByAddress.PersonResponseDto;
import com.safetynet.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PersonMapper {

    GetPersonByStationResponseDto toGetPersonByStationResponseDto(Person person);

    List<GetPersonByStationResponseDto> toGetPersonByStationResponseDtos(List<Person> people);

    PersonResponseDto toPersonResponseDto(Person person);

    default List<ChildrenResponseDto> toChildrenResponseDtos(List<Person> people) {
        List<ChildrenResponseDto> responseDtos = new ArrayList<>();
        List<Person> children = people.stream().filter(Person::isChild).toList();
        children.forEach(child -> {
            ChildrenResponseDto responseDto = toChildrenResponseDto(child);
            List<Person> otherMembers = people.stream().filter(person -> !person.equals(child)).toList();
            responseDto.setOtherMembers(otherMembers.stream().map(this::toPersonResponseDto).toList());
            responseDtos.add(responseDto);
        });
        return responseDtos;
    }

    @Mapping(target = "otherMembers", ignore = true)
    ChildrenResponseDto toChildrenResponseDto(Person child);

}