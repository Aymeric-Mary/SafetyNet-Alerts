package com.safetynet.service;

import com.safetynet.dto.personInfo.PersonInfoResponseDto;
import com.safetynet.mapper.PersonInfoMapper;
import com.safetynet.model.Person;
import com.safetynet.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonInfoService {
    private final PersonRepository personRepository;

    private final PersonInfoMapper personInfoMapper;

    public PersonInfoResponseDto getPersonInfoResponseDto(String firstName, String lastName) {
        List<Person> people = getPeople(firstName, lastName);
        return PersonInfoResponseDto.builder()
                .people(personInfoMapper.toPersonResponseDtoList(people))
                .build();
    }

    private List<Person> getPeople(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return personRepository.findByFirstNameAndLastName(firstName, lastName);
        }
        if (firstName != null) {
            return personRepository.findByFirstName(firstName);
        }
        if (lastName != null) {
            return personRepository.findByLastName(lastName);
        }
        throw new IllegalArgumentException("firstName or lastName is mandatory");
    }
}
