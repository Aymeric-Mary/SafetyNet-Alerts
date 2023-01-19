package com.safetynet.mapper;

import com.safetynet.dto.childAlert.ChildResponseDto;
import com.safetynet.dto.childAlert.PersonResponseDto;
import com.safetynet.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ChildAlertMapper {

    PersonResponseDto toPersonResponseDto(Person person);

    default List<ChildResponseDto> toChildrenResponseDtos(List<Person> people) {
        List<ChildResponseDto> responseDtos = new ArrayList<>();
        List<Person> children = people.stream().filter(Person::isChild).toList();
        children.forEach(child -> {
            ChildResponseDto responseDto = toChildrenResponseDto(child);
            List<Person> otherMembers = people.stream().filter(person -> !person.equals(child)).toList();
            responseDto.setOtherMembers(otherMembers.stream().map(this::toPersonResponseDto).toList());
            responseDtos.add(responseDto);
        });
        return responseDtos;
    }

    @Mapping(target = "otherMembers", ignore = true)
    ChildResponseDto toChildrenResponseDto(Person child);

}
